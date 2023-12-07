package nl.fontys.atosgame.cardservice.seeder;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import nl.fontys.atosgame.cardservice.CardServiceApplication;
import nl.fontys.atosgame.cardservice.dto.CreateCardDto;
import nl.fontys.atosgame.cardservice.dto.CreateCardSetDto;
import nl.fontys.atosgame.cardservice.enums.TagType;
import nl.fontys.atosgame.cardservice.model.Card;
import nl.fontys.atosgame.cardservice.model.CardSet;
import nl.fontys.atosgame.cardservice.model.Tag;
import nl.fontys.atosgame.cardservice.service.CardService;
import nl.fontys.atosgame.cardservice.service.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toCollection;

@Service
@Profile("development")
public class CardSeeder {

    private CardService cardService;
    private CardSetService cardSetService;
    private List<CardSet> oldCards;

    ResourceLoader resourceLoader = new DefaultResourceLoader();
    List<Tag> tags;

    public CardSeeder(
        @Autowired CardService cardService,
        @Autowired CardSetService cardSetService
    ) {
        this.cardService = cardService;
        this.cardSetService = cardSetService;
        oldCards = new ArrayList<>(cardSetService.getAll());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void seedCards() throws IOException {
        tags = AddTags("game", "personal", "color");
        AddCards("classpath:data/cards/roundOne.json", "roundOneCards", tags);

        tags = AddTags("advice", "personal", "color");
        AddCards("classpath:data/cards/roundOneAdvice.json", "roundOneCardsAdvice", tags);

        tags = AddTags("game", "group", "color");
        AddCards("classpath:data/cards/roundTwo.json", "roundTwoCards", tags);

        tags = AddTags("advice", "group", "color");
        AddCards("classpath:data/cards/roundOneAdvice.json", "roundTwoCardsAdvice", tags);

        tags = AddTags("game", "group", "operatingModel");
        AddCards("classpath:data/cards/roundThree.json", "roundThreeCards", tags);

        tags = AddTags("advice", "group", "operatingModel");
        AddCards("classpath:data/cards/roundThreeAdvice.json", "roundThreeCardsAdvice", tags);
    }

    private void AddCards(String classpath, String setName, List<Tag> tags) throws IOException
    {
        //Gets the cards from the json and create a list of CardDTOs
        Resource resource = resourceLoader.getResource(classpath);
        InputStream inputStream = resource.getInputStream();
        List<CreateCardDto> JsonCards = CardJsonReader.readCards(inputStream);

        //Convert CardDTOs to cards
        List<Card> newCards = new ArrayList<>();
        for (CreateCardDto card : JsonCards) {
            newCards.add(new Card(null, card.getTags(), card.getTranslations()));
        }

        //If there are cards in the database do the checksum
        if(!oldCards.isEmpty())
        {
            if(Checksum(setName, newCards))
            {
                return;
            }
        }

        //Get current incorrect card set
        CardSet currentSet = new CardSet();
        List<UUID> ids = new ArrayList<>();
        for(CardSet set : oldCards)
        {
            if(Objects.equals(set.getName(), setName))
            {
                currentSet = set;
            }
        }

        //Get all ids from current cards
        for(Card card : currentSet.getCards())
        {
            ids.add(card.getId());
        }

        //Delete set and associated cards
        if(!currentSet.getCards().isEmpty())
        {
            cardSetService.deleteCardSet(currentSet.getId());
            cardService.deleteCards(ids);
        }

        // Create the cards
        List<Card> addedCards = new ArrayList<>();
        for(Card card: newCards)
        {
            addedCards.add(cardService.createCard(card));
        }

        // Create the card set
        CreateCardSetDto cardSet = new CreateCardSetDto(
                setName,
                tags,
                addedCards
                        .stream()
                        .map(Card::getId)
                        .collect(toCollection(ArrayList::new))
        );
        cardSetService.createCardSet(cardSet);

        //Clear the tag list for the next function
        tags.clear();
    }

    private boolean Checksum(String setName, List<Card> newCards)
    {
        //Get the current old set from the list of old sets
        List<Card> cardSetOld = new ArrayList<>();
        for(CardSet set : oldCards)
        {
            if(Objects.equals(set.getName(), setName))
            {
                cardSetOld = new ArrayList<>(set.getCards());
            }
        }

        //Create a hash of old set and new set, compare and return result
        return CreateHash(cardSetOld) == CreateHash(newCards);
    }

    private List<Tag> AddTags(String value1, String value2, String value3)
    {
        List<Tag> tags = new ArrayList<>();
        Tag tag1 = new Tag();
        // type tag
        tag1.setTagKey(TagType.TYPE);
        tag1.setTagValue(value1);

        // group or personal tag
        Tag tag2 = new Tag();
        tag2.setTagKey(TagType.GROUP_OR_PERSONAL);
        tag2.setTagValue(value2);

        // important tag
        Tag tag3 = new Tag();
        tag3.setTagKey(TagType.IMPORTANT_TAG);
        tag3.setTagValue(value3);

        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);

        return tags;
    }

    private int CreateHash(List<Card> setToHash)
    {
        //Create new lists to hold the card variables
        List<String> dutchCards = new ArrayList<>();
        List<String> englishCards = new ArrayList<>();
        List<String> tags = new ArrayList<>();

        //Get the translations + tags from the cards
        for(Card card : setToHash)
        {
            dutchCards.add((new ArrayList<>(card.getTranslations()).get(0).getText()));
            englishCards.add((new ArrayList<>(card.getTranslations()).get(1).getText()));
            tags.add(new ArrayList<>(card.getTags()).get(0).getTagValue());
        }

        //Sort them to make sure both hashes are made from the same ordering
        Collections.sort(dutchCards);
        Collections.sort(englishCards);
        Collections.sort(tags);

        return dutchCards.hashCode() + englishCards.hashCode() + tags.hashCode();
    }
}
