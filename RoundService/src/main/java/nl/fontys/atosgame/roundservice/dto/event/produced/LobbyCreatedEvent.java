package nl.fontys.atosgame.roundservice.dto.event.produced;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.dto.event.BaseEvent;
import nl.fontys.atosgame.lobbyservice.model.Lobby;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LobbyCreatedEvent extends BaseEvent {
    private Lobby lobby;
    private UUID gameId;
}
