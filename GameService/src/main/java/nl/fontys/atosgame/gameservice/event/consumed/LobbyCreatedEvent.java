package nl.fontys.atosgame.gameservice.event.consumed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.event.BaseEvent;
import nl.fontys.atosgame.gameservice.model.Lobby;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LobbyCreatedEvent extends BaseEvent {
    private Lobby lobby;
    private UUID gameId;
}
