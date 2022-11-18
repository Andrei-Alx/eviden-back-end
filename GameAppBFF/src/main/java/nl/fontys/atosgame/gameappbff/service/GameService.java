package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.model.Game;

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
    Game handleGameCreated(UUID gameId);

    /**
     * Start a game in the database.
     * @param gameId The game to start.
     */
    Game handleGameStarted(UUID gameId);

    /**
     * End a game in the database.
     * @param gameId The game to end.
     */
    Game handleGameEnded(UUID gameId);
}
