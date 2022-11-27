package nl.fontys.atosgame.roundservice.event;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.model.Lobby;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LobbyCreatedEvent extends BaseEvent {

    private Lobby lobby;
    private UUID gameId;
}
