package nl.fontys.atosgame.gameservice.event;

import nl.fontys.atosgame.gameservice.model.LobbySettings;
import nl.fontys.atosgame.gameservice.model.RoundSettings;

import java.util.List;
import java.util.UUID;

public class GameCreatedEvent extends BaseEvent {
    private UUID gameId;
    private List<RoundSettings> roundSettings;
    private LobbySettings lobbySettings;
}
