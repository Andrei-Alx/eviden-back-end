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

    public void AddCards(String classpath, String setName, List<Tag> tags) throws IOException
    {
        Resource resource = resourceLoader.getResource(classpath);
        InputStream inputStream = resource.getInputStream();
        List<CreateCardDto> cards = CardJsonReader.readCards(inputStream);

        if(!oldCards.isEmpty())
        {
            if(ChecksumCheck(setName, cards))
            {
                return;
            }
        }

        List<Card> createdCards = new ArrayList<>();
        // Create the cards
        for (CreateCardDto card : cards) {
            createdCards.add(cardService.createCard(card));
        }

        // Create the card set
        CreateCardSetDto cardSet = new CreateCardSetDto(
                setName,
                tags,
                createdCards
                        .stream()
                        .map(Card::getId)
                        .collect(toCollection(ArrayList::new))
        );
        cardSetService.createCardSet(cardSet);

        tags.clear();
    }

    public boolean ChecksumCheck(String setName, List<CreateCardDto> cards)
    {

        if(CreateChecksum(setName, cards))
        {
            return true;
        }

        CardSet currentSet = new CardSet();
        List<UUID> ids = new ArrayList<>();
        for(CardSet set : oldCards)
        {
            if(Objects.equals(set.getName(), setName))
            {
                currentSet = set;
            }
        }

        for(Card card : currentSet.getCards())
        {
            ids.add(card.getId());
        }

        if(!currentSet.getCards().isEmpty())
        {
            cardSetService.deleteCardSet(currentSet.getId());
            cardService.deleteCards(ids);
        }
        return false;
    }

    public boolean CreateChecksum(String setName, List<CreateCardDto> cards)
    {
        List<Card> cardSetOld = new ArrayList<>();

        List<String> dutchCards = new ArrayList<>();
        List<String> englishCards = new ArrayList<>();
        List<String> tags = new ArrayList<>();

        for(CardSet set : oldCards)
        {
            if(Objects.equals(set.getName(), setName))
            {
                cardSetOld = new ArrayList<>(set.getCards());
            }
        }

        for(Card card : cardSetOld)
        {
            dutchCards.add((new ArrayList<>(card.getTranslations()).get(0).getText()));
            englishCards.add((new ArrayList<>(card.getTranslations()).get(1).getText()));
            tags.add(new ArrayList<>(card.getTags()).get(0).getTagValue());
        }

        Collections.sort(dutchCards);
        Collections.sort(englishCards);
        Collections.sort(tags);

        int hashOld = dutchCards.hashCode() + englishCards.hashCode() + tags.hashCode();

        dutchCards.clear();
        englishCards.clear();
        tags.clear();

        for(CreateCardDto card : cards)
        {
            dutchCards.add(new ArrayList<>(card.getTranslations()).get(0).getText());
            englishCards.add(new ArrayList<>(card.getTranslations()).get(1).getText());
            tags.add(new ArrayList<>(card.getTags()).get(0).getTagValue());
        }

        Collections.sort(dutchCards);
        Collections.sort(englishCards);
        Collections.sort(tags);

        int hashNew = dutchCards.hashCode() + englishCards.hashCode() + tags.hashCode();

        if(hashOld == hashNew)
        {
            return true;
        }
        else{
            return false;
        }
    }

    public List<Tag> AddTags(String value1, String value2, String value3)
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
}
