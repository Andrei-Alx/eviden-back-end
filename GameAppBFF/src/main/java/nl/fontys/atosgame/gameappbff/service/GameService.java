package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.model.Game;
import nl.fontys.atosgame.gameappbff.model.Lobby;

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

    /**
     * Add a lobby to a game in the database.
     * @param gameId The game to add the lobby to.
     * @param lobby The lobby to add.
     * @return The game with the added lobby.
     */
    Game addLobbyToGame(UUID gameId, Lobby lobby);
}
