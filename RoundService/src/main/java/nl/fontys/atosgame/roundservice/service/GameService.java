package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.model.Game;
import nl.fontys.atosgame.roundservice.model.Lobby;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

public interface GameService {
    /**
     * Initialize a game
     * @param gameId The id of the game
     * @param roundSettings The settings for the rounds
     * @return The initialized game
     */
    Game initializeGame(UUID gameId, List<RoundSettingsDto> roundSettings);

    /**
     * Add a lobby to a game
     * @param gameId The id of the game
     * @param lobby The lobby to add
     * @return The updated game
     */
    Game addLobbyToGame(UUID gameId, Lobby lobby);

    /**
     * Start the game
     * Start the first round
     * @param gameId The id of the game
     * @return The updated game
     */
    Game startGame(UUID gameId) throws EntityNotFoundException;

    /**
     * Start a round in a game
     * @param gameId The id of the game
     * @param roundNumber The number of the round
     * @return The updated game
     * @throws EntityNotFoundException When the game or round is not found
     */
    Game startRound(UUID gameId, int roundNumber) throws EntityNotFoundException;
}
