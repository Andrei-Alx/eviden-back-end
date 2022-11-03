package nl.fontys.atosgame.gameservice.event;

import nl.fontys.atosgame.cardservice.event.BaseEvent;
import nl.fontys.atosgame.lobbyservice.model.LobbySettings;
import nl.fontys.atosgame.roundservice.model.RoundSettings;

import java.util.List;
import java.util.UUID;

public class GameCreatedEvent extends BaseEvent {
    private UUID gameId;
    private List<RoundSettings> roundSettings;
    private LobbySettings lobbySettings;
}
