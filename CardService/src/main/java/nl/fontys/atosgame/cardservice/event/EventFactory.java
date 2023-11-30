package nl.fontys.atosgame.cardservice.event;

import java.util.List;

import nl.fontys.atosgame.cardservice.event.produced.CardSetEvent;
import nl.fontys.atosgame.cardservice.model.CardSet;

/**
 * Factory class for creating BaseEvent objects
 * @author Eli
 */
public class EventFactory {
    public static CardSetEvent createCardSetEvent(String service, List<CardSet> currentCards) {
        CardSetEvent event = new CardSetEvent();
        event =
                (CardSetEvent) initializeBaseEvent(event, "CardSet", service);
        event.setCardSets(currentCards);
        return event;
    }

    private static BaseEvent initializeBaseEvent(
        BaseEvent event,
        String type,
        String service
    ) {
        event.setTimestamp(java.time.LocalDateTime.now());
        event.setId(java.util.UUID.randomUUID());
        event.setType(type);
        event.setService(service);
        return event;
    }
}
