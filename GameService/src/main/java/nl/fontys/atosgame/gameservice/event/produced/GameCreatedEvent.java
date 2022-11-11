package nl.fontys.atosgame.gameservice.event.produced;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.event.BaseEvent;
import nl.fontys.atosgame.gameservice.model.LobbySettings;
import nl.fontys.atosgame.gameservice.model.RoundSettings;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameCreatedEvent extends BaseEvent {
    private UUID gameId;
    private List<RoundSettings> roundSettings;
    private LobbySettings lobbySettings;
}
