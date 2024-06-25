package nl.fontys.atosgame.gameservice.cardseeder;

import nl.fontys.atosgame.gameservice.model.CardSet;
import nl.fontys.atosgame.gameservice.service.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardSeeder {
    private final CardSetService cardSetService;

    public CardSeeder(
            @Autowired CardSetService cardSetService
    ) {
        this.cardSetService = cardSetService;
    }

    public void handleCardSet(List<CardSet> currentCards) throws IOException {
        List<CardSet> oldCards = new ArrayList<>(cardSetService.getAllCardSets());

        if (oldCards.isEmpty()) {
            cardSetService.createCardSets(currentCards);
            return;
        }

        cardSetService.deleteAll();
        cardSetService.createCardSets(currentCards);
    }
}
