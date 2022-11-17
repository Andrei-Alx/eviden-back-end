package nl.fontys.atosgame.roundservice.service;

import java.util.List;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.model.LobbySettings;
import nl.fontys.atosgame.roundservice.model.Round;

/**
 * Service for round related operations
 */
public interface RoundService {
    /**
     * Create rounds for a game
     * @param gameId The id of the game
     * @param roundSettings The settings for the rounds
     */
    List<Round> createRounds(UUID gameId, List<RoundSettingsDto> roundSettings);

    /**
     * Start a round
     * @param roundId The id of the round
     * @param playerIds the ids of the players to start the round for
     * @param gameId the id of the game
     * @return The updated round
     */
    Round startRound(UUID roundId, List<UUID> playerIds, UUID gameId);

    /**
     * End a round
     * @param roundId The id of the round
     * @param gameId The id of the game
     * @return The updated round
     */
    Round endRound(UUID roundId, UUID gameId);
}
