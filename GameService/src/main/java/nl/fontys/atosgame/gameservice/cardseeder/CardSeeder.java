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
    private CardSetService cardSetService;
    private CardSetEventService cardSetEventService;
    private List<CardSet> oldCards;

    public CardSeeder(
            @Autowired CardSetService cardSetService,
            @Autowired CardSetEventService cardSetEventService
    ) {
        this.cardSetService = cardSetService;
        this.cardSetEventService = cardSetEventService;
        oldCards = cardSetService.getAllCardSets();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void seedCards() {
        cardSetService.cardSetRequest();
    }

    //When receiving a new card-set the old id's will always be thrown out, and it will insert new ones.
    public void handleCardSet(List<CardSet> currentCards) throws IOException
    {
        List<String> idsOld = new ArrayList<>();
        List<String> idsNew = new ArrayList<>();
        for(CardSet set : oldCards)
        {
            idsOld.add(set.getId().toString());
        }

        for(CardSet set : currentCards)
        {
            idsNew.add(set.getId().toString());
        }

        Collections.sort(idsOld);
        Collections.sort(idsNew);

        if(idsOld.hashCode() == idsNew.hashCode())
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
