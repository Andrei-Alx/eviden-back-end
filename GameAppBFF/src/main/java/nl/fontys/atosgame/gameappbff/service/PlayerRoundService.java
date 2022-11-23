package nl.fontys.atosgame.gameappbff.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.gameappbff.model.PlayerRound;

/**
 * Service that manages playerRounds
 */
public interface PlayerRoundService {
    /**
     * Create a playerRound
     * @param playerId The id of the player
     * @param roundId The id of the round
     * @return The created playerRound
     */
    PlayerRound createPlayerRound(UUID playerId, UUID roundId);

    /**
     * Starts a playerRound. Handles creating a playerRound if it doesn't exist yet
     * @param playerId The id of the player
     * @param roundId The id of the round
     * @param gameId The id of the game
     * @param phaseNumber The phase number
     * @return The updated playerRound
     */
    PlayerRound startPhase(UUID playerId, UUID roundId, UUID gameId, int phaseNumber);

    /**
     * Ends a playerRound
     * @param playerId The id of the player
     * @param roundId The id of the round
     * @param gameId The id of the game
     * @param phaseNumber The phase number
     * @return The updated playerRound
     */
    PlayerRound endPhase(UUID playerId, UUID roundId, UUID gameId, int phaseNumber);

    /**
     * Gets a playerRound
     * @param playerId The id of the player
     * @param roundId The id of the round
     * @return The playerRound
     */
    Optional<PlayerRound> getPlayerRound(UUID playerId, UUID roundId);

    /**
     * Add cards to distributed cards
     * @param playerId The id of the player
     * @param roundId The id of the round
     * @param gameId The id of the game
     * @param cardIds The ids of the cards
     * @return The updated playerRound
     */
    PlayerRound distributeCards(
        UUID playerId,
        UUID roundId,
        UUID gameId,
        List<UUID> cardIds
    );

    /**
     * Add cards to liked cards
     * @param playerId The id of the player
     * @param roundId The id of the round
     * @param gameId The id of the game
     * @param cardId The id of the card
     * @return The updated playerRound
     */
    PlayerRound likeCard(UUID playerId, UUID roundId, UUID gameId, UUID cardId);

    /**
     * Add cards to disliked cards
     * @param playerId The id of the player
     * @param roundId The id of the round
     * @param gameId The id of the game
     * @param cardId The id of the card
     * @return The updated playerRound
     */
    PlayerRound dislikeCard(UUID playerId, UUID roundId, UUID gameId, UUID cardId);

    /**
     * Add cards to picked cards
     * @param playerId The id of the player
     * @param roundId The id of the round
     * @param gameId The id of the game
     * @param cardIds The ids of the cards
     * @return The updated playerRound
     */
    PlayerRound selectCards(UUID playerId, UUID roundId, UUID gameId, List<UUID> cardIds);
}
