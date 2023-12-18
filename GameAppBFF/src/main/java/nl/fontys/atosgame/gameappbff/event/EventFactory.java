package nl.fontys.atosgame.gameappbff.event;

import nl.fontys.atosgame.gameappbff.event.produced.CardSetRequestEvent;

import java.util.UUID;

/**
 * Factory class for creating BaseEvent objects
 * @author Eli
 */
public class EventFactory {
    public static CardSetRequestEvent cardsRequestEvent() {
        CardSetRequestEvent event = new CardSetRequestEvent();
        event =
                (CardSetRequestEvent) initializeBaseEvent(event, "CardSetRequest", "GameAppBFF");
        return event;
    }

    private static BaseEvent initializeBaseEvent(
        BaseEvent event,
        String type,
        String service
    ) {
        event.setTimestamp(java.time.LocalDateTime.now());
        event.setId(UUID.randomUUID());
        event.setType(type);
        event.setService(service);
        return event;
    }
}
