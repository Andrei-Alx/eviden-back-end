package nl.fontys.atosgame.cardservice.seeder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import nl.fontys.atosgame.cardservice.dto.CreateCardDto;
import nl.fontys.atosgame.cardservice.dto.CreateCardSetDto;
import nl.fontys.atosgame.cardservice.enums.TagType;
import nl.fontys.atosgame.cardservice.model.Card;
import nl.fontys.atosgame.cardservice.model.CardSet;
import nl.fontys.atosgame.cardservice.model.Tag;
import nl.fontys.atosgame.cardservice.service.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class CardSeeder {

	private final CardSetService cardSetService;

    ResourceLoader resourceLoader = new DefaultResourceLoader();
    List<Tag> tags;

    public CardSeeder(
        @Autowired CardSetService cardSetService
    ) {
		this.cardSetService = cardSetService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void seedCards() throws IOException {
        long cardSetCount = this.cardSetService.getCardSetCount();
        if (cardSetCount != 0) {
            return;
        }

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
        List<Card> newCards = JsonCards.stream()
                .map(card -> Card.builder()
                        .tags(card.getTags())
                        .translations(card.getTranslations())
                        .isActive(true)
                        .build())
                .toList();

        // Create the card set
        CreateCardSetDto cardSet = new CreateCardSetDto(
                setName,
                tags,
                newCards
        );
        cardSetService.createCardSet(cardSet);

        //Clear the tag list for the next function
        tags.clear();
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

}
