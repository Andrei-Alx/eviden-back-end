package nl.fontys.atosgame.cardservice.seeder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nl.fontys.atosgame.cardservice.dto.CreateCardDto;
import nl.fontys.atosgame.cardservice.dto.CreateCardSetDto;
import nl.fontys.atosgame.cardservice.enums.TagType;
import nl.fontys.atosgame.cardservice.model.Card;
import nl.fontys.atosgame.cardservice.model.Tag;
import nl.fontys.atosgame.cardservice.service.CardService;
import nl.fontys.atosgame.cardservice.service.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@Profile("development")
public class CardSeeder {

    private CardService cardService;
    private CardSetService cardSetService;

    @Value("classpath:data/cards/roundOne.json")
    private Resource roundOne;

    @Value("classpath:data/cards/roundOneAdvice.json")
    private Resource roundOneAdvice;

    @Value("classpath:data/cards/roundTwo.json")
    private Resource roundTwo;


    public CardSeeder(
            @Autowired CardService cardService,
            @Autowired CardSetService cardSetService
    ) {
        this.cardService = cardService;
        this.cardSetService = cardSetService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void seedCards() throws IOException {
        // Read the cards from the json file
        List<CreateCardDto> cards = CardJsonReader.readCards(roundOne.getFile());
        List<Card> createdCards = new ArrayList<>();
        // Create the cards
        for (CreateCardDto card : cards) {
            createdCards.add(cardService.createCard(card));
        }

        // create tags
        List<Tag> tags1 = new ArrayList<>();
        Tag tag = new Tag();
        // type tag
        tag.setTagKey(TagType.TYPE);
        tag.setTagValue("game");
        // group or personal tag
        Tag tag2 = new Tag();
        tag2.setTagKey(TagType.GROUP_OR_PERSONAL);
        tag2.setTagValue("personal");
        // important tag
        Tag tag3 = new Tag();
        tag3.setTagKey(TagType.IMPORTANT_TAG);
        tag3.setTagValue("color");

        tags1.add(tag);
        tags1.add(tag2);
        tags1.add(tag3);

        // Create the card set
        CreateCardSetDto cardSet = new CreateCardSetDto(
                "roundOneCards",
                tags1,
                createdCards
                        .stream()
                        .map(Card::getId)
                        .collect(Collectors.toCollection(ArrayList::new))
        );
        cardSetService.createCardSet(cardSet);


        // create tags
        List<Tag> tags1Advice = new ArrayList<>();
        Tag tag4 = new Tag();
        // type tag
        tag4.setTagKey(TagType.TYPE);
        tag4.setTagValue("advice");
        // group or personal tag
        Tag tag5 = new Tag();
        tag5.setTagKey(TagType.GROUP_OR_PERSONAL);
        tag5.setTagValue("personal");
        // important tag
        Tag tag6 = new Tag();
        tag6.setTagKey(TagType.IMPORTANT_TAG);
        tag6.setTagValue("color");

        tags1Advice.add(tag4);
        tags1Advice.add(tag5);
        tags1Advice.add(tag6);

        // Card set for round one advice cards
        cards = CardJsonReader.readCards(roundOneAdvice.getFile());
        createdCards = new ArrayList<>();
        for (CreateCardDto card : cards) {
            createdCards.add(cardService.createCard(card));
        }
        cardSet =
                new CreateCardSetDto(
                        "roundOneCardsAdvice",
                        tags1Advice,
                        createdCards
                                .stream()
                                .map(Card::getId)
                                .collect(Collectors.toCollection(ArrayList::new))
                );
        cardSetService.createCardSet(cardSet);

        // create tags
        List<Tag> tags2 = new ArrayList<>();
        Tag tag7 = new Tag();
        // type tag
        tag7.setTagKey(TagType.TYPE);
        tag7.setTagValue("game");
        // group or personal tag
        Tag tag8 = new Tag();
        tag8.setTagKey(TagType.GROUP_OR_PERSONAL);
        tag8.setTagValue("personal");
        // important tag
        Tag tag9 = new Tag();
        tag9.setTagKey(TagType.IMPORTANT_TAG);
        tag9.setTagValue("color");

        tags2.add(tag7);
        tags2.add(tag8);
        tags2.add(tag9);

        // Do same for round two
        cards = CardJsonReader.readCards(roundTwo.getFile());
        createdCards = new ArrayList<>();
        for (CreateCardDto card : cards) {
            createdCards.add(cardService.createCard(card));
        }
        cardSet =
                new CreateCardSetDto(
                        "roundTwoCards",
                        tags2,
                        createdCards
                                .stream()
                                .map(Card::getId)
                                .collect(Collectors.toCollection(ArrayList::new))
                );
        cardSetService.createCardSet(cardSet);
    }
}
