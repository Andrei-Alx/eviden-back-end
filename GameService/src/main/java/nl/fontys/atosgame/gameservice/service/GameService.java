package nl.fontys.atosgame.gameservice.service;

import nl.fontys.atosgame.gameservice.model.Game;
import nl.fontys.atosgame.gameservice.model.LobbySettings;
import nl.fontys.atosgame.gameservice.model.RoundSettings;

import java.util.List;
import java.util.UUID;

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
    Game createGame(String title, String companyType, LobbySettings lobbySettings, List<RoundSettings> roundSettings);

    /**
     * Starts a game
     * @param gameId The id of the game
     * @return The started game
     */
    Game startGame(UUID gameId);
}
