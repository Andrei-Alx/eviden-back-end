package nl.fontys.atosgame.roundservice.event;


import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.model.LobbySettings;

import java.util.List;
import java.util.UUID;

public class GameCreatedEvent extends BaseEvent {
    private UUID gameId;
    private List<RoundSettingsDto> roundSettings;
    private LobbySettings lobbySettings;
}
