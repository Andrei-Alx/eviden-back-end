package nl.fontys.atosgame.lobbyservice.event.consumed;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.lobbyservice.event.BaseEvent;
import nl.fontys.atosgame.lobbyservice.model.LobbySettings;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameCreatedEvent extends BaseEvent {

    private LobbySettings lobbySettings;
    private UUID gameId;
}
