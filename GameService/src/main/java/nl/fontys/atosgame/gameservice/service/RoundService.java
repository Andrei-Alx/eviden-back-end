package nl.fontys.atosgame.gameservice.service;

import nl.fontys.atosgame.gameservice.model.Round;

import java.util.UUID;

/**
 * Service for handling rounds
 */
public interface RoundService {

    /**
     * Start a round
     * @param roundId The id of the round
     */
    void startRound(UUID roundId);

    /**
     * End a round
     * @param roundId The id of the round
     */
    void endRound(UUID roundId);

    /**
     * Create a new round and add it to the game
     * @param gameId The id of the game
     * @param round The round
     */
    void createRound(UUID gameId, Round round);
}
