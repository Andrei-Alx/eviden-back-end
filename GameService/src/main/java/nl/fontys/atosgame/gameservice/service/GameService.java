package nl.fontys.atosgame.gameservice.service;

import java.util.List;
import java.util.UUID;

import nl.fontys.atosgame.gameservice.exceptions.EmptyStringException;
import nl.fontys.atosgame.gameservice.model.Game;
import nl.fontys.atosgame.gameservice.model.LobbySettings;
import nl.fontys.atosgame.gameservice.model.Round;
import nl.fontys.atosgame.gameservice.model.RoundSettings;

/**
 * Service for managing games.
 */
public interface GameService {
    /**
     * Creates a new game
     * @param title The title of the game
     * @param companyType The type of company
     * @param lobbySettings The settings for the lobby
     * @param roundSettings The settings for the rounds
     * @return The created game
     */
    Game createGame(
        String title,
        String companyType,
        LobbySettings lobbySettings,
        List<RoundSettings> roundSettings
    ) throws EmptyStringException;

    /**
     * Starts a game
     * @param gameId The id of the game
     * @return The started game
     */
    Game startGame(UUID gameId);

    /**
     * Ends a game
     * @param gameId The id of the game
     * @return The ended game
     */
    Game endGame(UUID gameId);

    /**
     * Add a round to a game
     * @param gameId The id of the game
     * @param round The round
     * @return The game with the added round
     */
    Game addRound(UUID gameId, Round round);
}
