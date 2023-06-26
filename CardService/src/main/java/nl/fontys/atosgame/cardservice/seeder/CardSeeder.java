package nl.fontys.atosgame.cardservice.seeder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nl.fontys.atosgame.cardservice.CardServiceApplication;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@Profile("development")
public class CardSeeder {

    private CardService cardService;
    private CardSetService cardSetService;

    @Value("classpath:data/cards/roundOne.json")
    private ClassPathResource roundOne;

    @Value("classpath:data/cards/roundOneAdvice.json")
    private ClassPathResource roundOneAdvice;

    @Value("classpath:data/cards/roundTwo.json")
    private ClassPathResource roundTwo;

    @Value("classpath:data/cards/roundOneAdvice.json")
    private ClassPathResource roundTwoAdvice;

    @Value("classpath:data/cards/roundThree.json")
    private ClassPathResource roundThree;

    @Value("classpath:data/cards/roundThreeAdvice.json")
    private ClassPathResource roundThreeAdvice;

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
        List<CreateCardDto> cards = CardJsonReader.readCards(roundOne.getInputStream());
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
        cards = CardJsonReader.readCards(roundOneAdvice.getInputStream());
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
        tag8.setTagValue("group");
        // important tag
        Tag tag9 = new Tag();
        tag9.setTagKey(TagType.IMPORTANT_TAG);
        tag9.setTagValue("color");

        tags2.add(tag7);
        tags2.add(tag8);
        tags2.add(tag9);

        // Do same for round two
        cards = CardJsonReader.readCards(roundTwo.getInputStream());
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

        // create tags
        List<Tag> tags3 = new ArrayList<>();
        Tag tag10 = new Tag();
        // type tag
        tag10.setTagKey(TagType.TYPE);
        tag10.setTagValue("advice");
        // group or personal tag
        Tag tag11 = new Tag();
        tag11.setTagKey(TagType.GROUP_OR_PERSONAL);
        tag11.setTagValue("group");
        // important tag
        Tag tag12 = new Tag();
        tag12.setTagKey(TagType.IMPORTANT_TAG);
        tag12.setTagValue("color");

        tags3.add(tag10);
        tags3.add(tag11);
        tags3.add(tag12);

        // Do same for round two advice cards
        cards = CardJsonReader.readCards(roundTwoAdvice.getInputStream());
        createdCards = new ArrayList<>();
        for (CreateCardDto card : cards) {
            createdCards.add(cardService.createCard(card));
        }
        cardSet =
            new CreateCardSetDto(
                "roundTwoCardsAdvice",
                tags3,
                createdCards
                    .stream()
                    .map(Card::getId)
                    .collect(Collectors.toCollection(ArrayList::new))
            );
        cardSetService.createCardSet(cardSet);

        // create tags
        List<Tag> tags4 = new ArrayList<>();
        Tag tag14 = new Tag();
        // type tag
        tag14.setTagKey(TagType.TYPE);
        tag14.setTagValue("game");
        // group or personal tag
        Tag tag15 = new Tag();
        tag15.setTagKey(TagType.GROUP_OR_PERSONAL);
        tag15.setTagValue("group");
        // important tag
        Tag tag16 = new Tag();
        tag16.setTagKey(TagType.IMPORTANT_TAG);
        tag16.setTagValue("operatingModel");

        tags4.add(tag14);
        tags4.add(tag15);
        tags4.add(tag16);

        // Do same for round three cards
        cards = CardJsonReader.readCards(roundThree.getInputStream());
        createdCards = new ArrayList<>();
        for (CreateCardDto card : cards) {
            createdCards.add(cardService.createCard(card));
        }
        cardSet =
            new CreateCardSetDto(
                "roundThreeCards",
                tags4,
                createdCards
                    .stream()
                    .map(Card::getId)
                    .collect(Collectors.toCollection(ArrayList::new))
            );
        cardSetService.createCardSet(cardSet);

        // create tags
        List<Tag> tags5 = new ArrayList<>();
        Tag tag17 = new Tag();
        // type tag
        tag17.setTagKey(TagType.TYPE);
        tag17.setTagValue("advice");
        // group or personal tag
        Tag tag18 = new Tag();
        tag18.setTagKey(TagType.GROUP_OR_PERSONAL);
        tag18.setTagValue("group");
        // important tag
        Tag tag19 = new Tag();
        tag19.setTagKey(TagType.IMPORTANT_TAG);
        tag19.setTagValue("operatingModel");

        tags5.add(tag17);
        tags5.add(tag18);
        tags5.add(tag19);

        // Do same for round three advice cards
        cards = CardJsonReader.readCards(roundThreeAdvice.getInputStream());
        createdCards = new ArrayList<>();
        for (CreateCardDto card : cards) {
            createdCards.add(cardService.createCard(card));
        }
        cardSet =
            new CreateCardSetDto(
                "roundThreeCardsAdvice",
                tags5,
                createdCards
                    .stream()
                    .map(Card::getId)
                    .collect(Collectors.toCollection(ArrayList::new))
            );
        cardSetService.createCardSet(cardSet);
    }
}
