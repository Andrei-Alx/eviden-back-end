package nl.fontys.atosgame.gameappbff.service;

import java.util.UUID;
import nl.fontys.atosgame.gameappbff.model.Game;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.model.Round;

/**
 * Service for handling games.
 * @author Aniek
 */
public interface GameService {
    /**
     * Create a new game in the database.
     * @param gameId, title The game to create.
     */
    Game handleGameCreated(UUID gameId, String title);

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

    /**
     * Add a round to a game in the database.
     * @param round The round to add.
     * @param gameId The game to add the round to.
     * @return The game with the added round.
     */
    Game addRoundToGame(Round round, UUID gameId);
}
