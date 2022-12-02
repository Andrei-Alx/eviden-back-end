package nl.fontys.atosgame.gameappbff.service;

import java.util.List;
import java.util.Optional;
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
     * @param gameId The id of the game to create
     * @param title  The title of the game to create
     * @param companyType The company type of the game to create
     * @return  The created game
     */
    Game handleGameCreated(UUID gameId, String title, String companyType);

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

    /**
     * Get all games from the database.
     * @return List of games.
     */
    List<Game> getAllGames();

    /**
     * Get a game from the database.
     * @param gameId The id of the game to get.
     * @return The game.
     */
    Optional<Game> getGame(UUID gameId);
}
