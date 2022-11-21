package nl.fontys.atosgame.gameappbff.service;

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
}
