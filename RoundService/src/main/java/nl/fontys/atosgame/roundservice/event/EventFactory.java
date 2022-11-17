package nl.fontys.atosgame.roundservice.event;

import java.util.List;
import java.util.UUID;

import nl.fontys.atosgame.roundservice.event.produced.PlayerCardsDistributed;
import nl.fontys.atosgame.roundservice.event.produced.RoundCreatedEvent;
import nl.fontys.atosgame.roundservice.model.Round;

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
        event = (RoundCreatedEvent) initializeBaseEvent(event, "RoundCreated", service);
        event.setRound(round);
        return event;
    }

    /**
     * Create a PlayerCardsDistributedEvent
     * @param type
     * @param service
     * @return
     */
    public static PlayerCardsDistributed createPlayerCardsDistributedEvent(UUID roundId, UUID playerId, UUID gameId, List<UUID> cardId) {
        PlayerCardsDistributed event = new PlayerCardsDistributed();
        event = (PlayerCardsDistributed) initializeBaseEvent(event, "PlayerCardsDistributed", "RoundService");
        event.setRoundId(roundId);
        event.setPlayerId(playerId);
        event.setGameId(gameId);
        event.setCardIds(cardId);
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
