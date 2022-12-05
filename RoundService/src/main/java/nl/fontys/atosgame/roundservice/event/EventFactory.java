package nl.fontys.atosgame.roundservice.event;

import java.util.List;
import java.util.UUID;

import nl.fontys.atosgame.roundservice.dto.ResultDto;
import nl.fontys.atosgame.roundservice.enums.ResultStatus;
import nl.fontys.atosgame.roundservice.event.produced.*;
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
     * @param gameId The id of the game the round was created for
     * @return The created event
     */
    public static RoundCreatedEvent createRoundCreatedEvent(
        String service,
        Round round,
        UUID gameId
    ) {
        RoundCreatedEvent event = new RoundCreatedEvent();
        event = (RoundCreatedEvent) initializeBaseEvent(event, "RoundCreated", service);
        event.setRound(round);
        event.setGameId(gameId);
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
    public static PlayerCardsDistributed createPlayerCardsDistributedEvent(
        UUID roundId,
        UUID playerId,
        UUID gameId,
        List<UUID> cardIds
    ) {
        PlayerCardsDistributed event = new PlayerCardsDistributed();
        event =
            (PlayerCardsDistributed) initializeBaseEvent(
                event,
                "PlayerCardsDistributed",
                "RoundService"
            );
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
        event =
            (RoundStartedEvent) initializeBaseEvent(
                event,
                "RoundStarted",
                "RoundService"
            );
        event.setRoundId(roundId);
        event.setGameId(gameId);
        return event;
    }

    public static RoundEndedEvent createRoundEndedEvent(UUID roundId, UUID gameId) {
        RoundEndedEvent event = new RoundEndedEvent();
        event =
            (RoundEndedEvent) initializeBaseEvent(event, "RoundEnded", "RoundService");
        event.setRoundId(roundId);
        event.setGameId(gameId);
        return event;
    }

    /**
     * Create a PlayerPhaseStartedEvent
     * @param phaseNumber The number of the phase
     * @param roundId The id of the round
     * @param gameId The id of the game
     * @param playerId The id of the player
     * @return The created event
     */
    public static PlayerPhaseStartedEvent createPlayerPhaseStartedEvent(
        int phaseNumber,
        UUID roundId,
        UUID gameId,
        UUID playerId
    ) {
        PlayerPhaseStartedEvent event = new PlayerPhaseStartedEvent();
        event =
            (PlayerPhaseStartedEvent) initializeBaseEvent(
                event,
                "PlayerPhaseStarted",
                "RoundService"
            );
        event.setPhaseNumber(phaseNumber);
        event.setRoundId(roundId);
        event.setGameId(gameId);
        event.setPlayerId(playerId);
        return event;
    }

    /**
     * Create a PlayerPhaseEndedEvent
     * @param phaseNumber The number of the phase
     * @param roundId The id of the round
     * @param gameId The id of the game
     * @param playerId The id of the player
     * @return The created event
     */
    public static PlayerPhaseEndedEvent createPlayerPhaseEndedEvent(
        int phaseNumber,
        UUID roundId,
        UUID gameId,
        UUID playerId
    ) {
        PlayerPhaseEndedEvent event = new PlayerPhaseEndedEvent();
        event =
            (PlayerPhaseEndedEvent) initializeBaseEvent(
                event,
                "PlayerPhaseEnded",
                "RoundService"
            );
        event.setPhaseNumber(phaseNumber);
        event.setRoundId(roundId);
        event.setGameId(gameId);
        event.setPlayerId(playerId);
        return event;
    }

    /**
     * Create a PlayerLikedCardEvent
     * @param roundId The id of the round
     * @param gameId The id of the game
     * @param playerId The id of the player
     * @param cardId The id of the card
     * @return The created event
     */
    public static PlayerLikedCard createPlayerLikedCardEvent(
        UUID roundId,
        UUID gameId,
        UUID playerId,
        UUID cardId
    ) {
        PlayerLikedCard event = new PlayerLikedCard();
        event =
            (PlayerLikedCard) initializeBaseEvent(
                event,
                "PlayerLikedCard",
                "RoundService"
            );
        event.setRoundId(roundId);
        event.setGameId(gameId);
        event.setPlayerId(playerId);
        event.setCardId(cardId);
        return event;
    }

    /**
     * Create a PlayerDislikedCardEvent
     * @param roundId The id of the round
     * @param gameId The id of the game
     * @param playerId The id of the player
     * @param cardId The id of the card
     * @return The created event
     */
    public static PlayerDislikedCard createPlayerDislikedCardEvent(
        UUID roundId,
        UUID gameId,
        UUID playerId,
        UUID cardId
    ) {
        PlayerDislikedCard event = new PlayerDislikedCard();
        event =
            (PlayerDislikedCard) initializeBaseEvent(
                event,
                "PlayerDislikedCard",
                "RoundService"
            );
        event.setRoundId(roundId);
        event.setGameId(gameId);
        event.setPlayerId(playerId);
        event.setCardId(cardId);
        return event;
    }

    /**
     * Create a PlayerSelectedCardsEvent
     * @param roundId The id of the round
     * @param gameId The id of the game
     * @param playerId The id of the player
     * @param cardIds The ids of the cards
     * @return The created event
     */
    public static PlayerSelectedCards createPlayerSelectedCardsEvent(
        UUID roundId,
        UUID gameId,
        UUID playerId,
        List<UUID> cardIds
    ) {
        PlayerSelectedCards event = new PlayerSelectedCards();
        event =
            (PlayerSelectedCards) initializeBaseEvent(
                event,
                "PlayerSelectedCards",
                "RoundService"
            );
        event.setRoundId(roundId);
        event.setGameId(gameId);
        event.setPlayerId(playerId);
        event.setCardIds(cardIds);
        return event;
    }

    /**
     * Create a PlayerResultDeterminedEvent
     * @param roundId The id of the round
     * @param gameId The id of the game
     * @param playerId The id of the player
     * @param result The result of the player
     * @return The created event
     */
    public static PlayerResultDeterminedEvent createPlayerResultDeterminedEvent(
            UUID roundId,
            UUID gameId,
            UUID playerId,
            ResultDto result
    ) {
        PlayerResultDeterminedEvent event = new PlayerResultDeterminedEvent();
        event =
                (PlayerResultDeterminedEvent) initializeBaseEvent(
                        event,
                        "PlayerResultDetermined",
                        "RoundService"
                );
        event.setRoundId(roundId);
        event.setGameId(gameId);
        event.setPlayerId(playerId);
        event.setResult(result);
        return event;
    }

    /**
     * Create a PlayerResultIndeterminedEvent
     * @param roundId The id of the round
     * @param gameId The id of the game
     * @param playerId The id of the player
     * @param resultStatus The result of the player
     * @return The created event
     */
    public static PlayerResultIndeterminateEvent createPlayerResultIndeterminedEvent(
            UUID roundId,
            UUID gameId,
            UUID playerId,
            ResultStatus resultStatus
    ) {
        PlayerResultIndeterminateEvent event = new PlayerResultIndeterminateEvent();
        event =
                (PlayerResultIndeterminateEvent) initializeBaseEvent(
                        event,
                        "PlayerResultIndetermined",
                        "RoundService"
                );
        event.setRoundId(roundId);
        event.setGameId(gameId);
        event.setPlayerId(playerId);
        event.setResultStatus(ResultStatus.INDETERMINED);
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
