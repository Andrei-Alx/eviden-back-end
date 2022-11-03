package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.model.LobbySettings;

import java.util.List;
import java.util.UUID;

public interface RoundService {
    void createRounds(UUID gameId, List<RoundSettingsDto> roundSettings, LobbySettings lobbySettings);
}
