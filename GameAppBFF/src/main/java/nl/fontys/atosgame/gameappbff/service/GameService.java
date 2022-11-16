package nl.fontys.atosgame.gameappbff.service;

import java.util.UUID;

/**
 * Service for handling games.
 * @author Aniek
 */
public interface GameService {

    /**
     * Create a new game in the database.
     * @param gameId The game to create.
     */
    void handleGameCreated(UUID gameId);

    /**
     * Start a game in the database.
     * @param gameId The game to start.
     */
    void handleGameStarted(UUID gameId);
}
