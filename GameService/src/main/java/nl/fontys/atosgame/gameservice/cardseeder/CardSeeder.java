package nl.fontys.atosgame.gameservice.cardseeder;

import nl.fontys.atosgame.gameservice.model.CardSet;
import nl.fontys.atosgame.gameservice.service.CardSetEventService;
import nl.fontys.atosgame.gameservice.service.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class CardSeeder {
    private final CardSetService cardSetService;

    public CardSeeder(
            @Autowired CardSetService cardSetService
    ) {
        this.cardSetService = cardSetService;
    }

    //When receiving a new card-set the old id's will always be thrown out, and it will insert new ones.
    public void handleCardSet(List<CardSet> currentCards) throws IOException
    {
        List<CardSet> oldCards = new ArrayList<>(cardSetService.getAllCardSets());

        if(currentCards.equals(oldCards))
        {
            return;
        }

        for(CardSet set : oldCards)
        {
            cardSetService.deleteCardSet(set.getId());
        }

        for(CardSet set : currentCards)
        {
            cardSetService.createCardSet(set);
        }
    }
}
