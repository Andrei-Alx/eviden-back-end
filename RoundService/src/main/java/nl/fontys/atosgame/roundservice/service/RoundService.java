package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.model.LobbySettings;
import nl.fontys.atosgame.roundservice.model.Round;

import java.util.List;
import java.util.UUID;

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
}
