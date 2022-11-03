package nl.fontys.atosgame.roundservice.event;


import nl.fontys.atosgame.roundservice.model.Round;

import java.util.UUID;

/**
 * Factory class for creating events
 * @author Eli
 */
public class EventFactory {

    /**
     * Create a RoundCreatedEvent
     * @param service The service that created the event
     * @param round The round that was created
     * @return The created event
     */
    public static RoundCreatedEvent createRoundCreatedEvent(String service, Round round) {
        RoundCreatedEvent event = new RoundCreatedEvent();
        event = (RoundCreatedEvent) initializeBaseEvent(event, "RoundCreated",service);
        event.setRound(round);
        return event;
    }

    private static BaseEvent initializeBaseEvent(BaseEvent event, String type, String service) {
        event.setTimestamp(java.time.LocalDateTime.now());
        event.setId(UUID.randomUUID());
        event.setType(type);
        event.setService(service);
        return event;
    }
}
