package nl.fontys.atosgame.lobbyservice.event.produced;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.lobbyservice.event.BaseEvent;
import nl.fontys.atosgame.lobbyservice.model.Lobby;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LobbyCreatedEvent extends BaseEvent {

    private Lobby lobby;

    private UUID gameId;
}
