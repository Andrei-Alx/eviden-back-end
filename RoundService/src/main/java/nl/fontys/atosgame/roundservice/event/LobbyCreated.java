package nl.fontys.atosgame.roundservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.model.Lobby;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LobbyCreated extends BaseEvent {
    private Lobby lobby;
    private UUID gameId;
}
