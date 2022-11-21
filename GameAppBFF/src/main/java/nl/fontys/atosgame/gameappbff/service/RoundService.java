package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.model.Round;

import java.util.UUID;

/**
 * Handle
 */
public interface RoundService {
    /**
     * Creates a new round for the given game
     * @param round The round to create
     * @param gameId The id of the game to create the round for
     */
    void handleRoundCreatedEvent(Round round, UUID gameId);

    /**
     * Updates the round with the given id
     * @param roundId The id of the round to update
     * @param gameId The id of the game the round belongs to
     */
    Round startRound(UUID roundId, UUID gameId);
}
