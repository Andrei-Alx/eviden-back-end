package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.model.LobbySettings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoundServiceImpl implements RoundService {
    @Override
    public void createRounds(UUID gameId, List<RoundSettingsDto> roundSettings, LobbySettings lobbySettings) {
        // TODO
    }
}
