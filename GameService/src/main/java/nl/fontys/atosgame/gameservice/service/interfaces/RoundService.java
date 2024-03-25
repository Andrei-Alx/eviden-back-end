package nl.fontys.atosgame.gameservice.service.interfaces;

import nl.fontys.atosgame.gameservice.model.Round;

import java.util.UUID;

/**
 * Service for handling rounds
 */
public interface RoundService {

    /**
     * Start a round
     * @param roundId The id of the round
     * @return The updated round
     */
    Round startRound(UUID roundId);

    /**
     * End a round
     * @param roundId The id of the round
     * @return The updated round
     */
    Round endRound(UUID roundId);

    /**
     * Create a new round and add it to the game
     * @param gameId The id of the game
     * @param round The round
     * @return The created round
     */
    Round createRound(UUID gameId, Round round);
}
