package nl.fontys.atosgame.cardservice.seeder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import nl.fontys.atosgame.cardservice.dto.CreateCardDto;
import nl.fontys.atosgame.cardservice.dto.CreateCardSetDto;
import nl.fontys.atosgame.cardservice.model.Card;
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
        // Create the card set
        CreateCardSetDto cardSet = new CreateCardSetDto(
            "RoundOneCards",
            "",
            createdCards
                .stream()
                .map(Card::getId)
                .collect(Collectors.toCollection(ArrayList::new))
        ,"color");
        cardSetService.createCardSet(cardSet);

        // Do same for round two
        cards = CardJsonReader.readCards(roundTwo.getFile());
        createdCards = new ArrayList<>();
        for (CreateCardDto card : cards) {
            createdCards.add(cardService.createCard(card));
        }
        cardSet =
            new CreateCardSetDto(
                "RoundTwoCards",
                "",
                createdCards
                    .stream()
                    .map(Card::getId)
                    .collect(Collectors.toCollection(ArrayList::new))
            , "color");
        cardSetService.createCardSet(cardSet);
    }
}
