package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.model.Game;

import java.util.List;
import java.util.UUID;

public interface GameService {
    Game initializeGame(UUID gameId, List<RoundSettingsDto> roundSettings);
}
