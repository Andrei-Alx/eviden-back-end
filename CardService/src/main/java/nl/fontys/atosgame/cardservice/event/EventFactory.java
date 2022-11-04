package nl.fontys.atosgame.cardservice.event;

import nl.fontys.atosgame.cardservice.model.Card;
import nl.fontys.atosgame.cardservice.model.CardSet;

import java.util.UUID;

/**
 * Factory class for creating BaseEvent objects
 * @author Eli
 */
public class EventFactory {

    public static CardEvent createCardCreatedEvent(String service, Card card) {
        CardEvent event = new CardEvent();
        event = (CardEvent) initializeBaseEvent(event, "CardCreated",service);
        event.setCard(card);
        return event;
    }

    public static CardEvent createCardUpdatedEvent(String service, Card card) {
        CardEvent event = new CardEvent();
        event = (CardEvent) initializeBaseEvent(event, "CardUpdated", service);
        event.setCard(card);
        return event;
    }

    public static CardDeletedEvent createCardDeletedEvent(String service, UUID id) {
        CardDeletedEvent event = new CardDeletedEvent();
        event = (CardDeletedEvent) initializeBaseEvent(event, "CardDeleted", service);
        event.setCardId(id);
        return event;
    }

    public static CardSetEvent createCardSetCreatedEvent(String service, CardSet cardSet) {
        CardSetEvent event = new CardSetEvent();
        event = (CardSetEvent) initializeBaseEvent(event, "CardSetCreated", service);
        event.setCardSet(cardSet);
        return event;
    }

    public static CardSetEvent createCardSetUpdatedEvent(String service, CardSet cardSet) {
        CardSetEvent event = new CardSetEvent();
        event = (CardSetEvent) initializeBaseEvent(event, "CardSetUpdated", service);
        event.setCardSet(cardSet);
        return event;
    }

    public static CardSetDeletedEvent createCardSetDeletedEvent(String service, UUID id) {
        CardSetDeletedEvent event = new CardSetDeletedEvent();
        event = (CardSetDeletedEvent) initializeBaseEvent(event, "CardSetDeleted", service);
        event.setCardSetId(id);
        return event;
    }

    private static BaseEvent initializeBaseEvent(BaseEvent event, String type, String service) {
        event.setTimestamp(java.time.LocalDateTime.now());
        event.setId(java.util.UUID.randomUUID());
        event.setType(type);
        event.setService(service);
        return event;
    }
}
