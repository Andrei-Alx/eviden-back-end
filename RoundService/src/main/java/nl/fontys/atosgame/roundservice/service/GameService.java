package nl.fontys.atosgame.roundservice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.EntityNotFoundException;
import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.model.Game;
import nl.fontys.atosgame.roundservice.model.Lobby;

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
     * Check if the next round should be started and start it if needed
     * @param gameId The id of the game
     */
    void checkForNextRound(UUID gameId);

    /**
     * Get the game of a round
     * @param roundId The id of the round
     * @return An optional containing the game if found
     */
    Optional<Game> getGameByRoundId(UUID roundId);
}
