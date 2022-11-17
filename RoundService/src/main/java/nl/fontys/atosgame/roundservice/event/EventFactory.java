package nl.fontys.atosgame.roundservice.event;

import java.util.List;
import java.util.UUID;

import nl.fontys.atosgame.roundservice.event.produced.PlayerCardsDistributed;
import nl.fontys.atosgame.roundservice.event.produced.RoundCreatedEvent;
import nl.fontys.atosgame.roundservice.event.produced.RoundStartedEvent;
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
     * Create a PlayerCardsDistibuted event
     * @param roundId The id of the round
     * @param playerId The id of the player
     * @param gameId The id of the game
     * @param cardIds The ids of the cards
     * @return
     */
    public static PlayerCardsDistributed createPlayerCardsDistributedEvent(UUID roundId, UUID playerId, UUID gameId, List<UUID> cardIds) {
        PlayerCardsDistributed event = new PlayerCardsDistributed();
        event = (PlayerCardsDistributed) initializeBaseEvent(event, "PlayerCardsDistributed", "RoundService");
        event.setRoundId(roundId);
        event.setPlayerId(playerId);
        event.setGameId(gameId);
        event.setCardIds(cardIds);
        return event;
    }

    /**
     * Create a RoundStartedEvent
     * @param roundId The id of the round
     * @param gameId The id of the game
     * @return
     */
    public static RoundStartedEvent createRoundStartedEvent(UUID roundId, UUID gameId) {
        RoundStartedEvent event = new RoundStartedEvent();
        event = (RoundStartedEvent) initializeBaseEvent(event, "RoundStarted", "RoundService");
        event.setRoundId(roundId);
        event.setGameId(gameId);
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
