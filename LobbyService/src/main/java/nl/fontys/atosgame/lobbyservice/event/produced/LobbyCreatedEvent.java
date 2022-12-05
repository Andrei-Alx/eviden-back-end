package nl.fontys.atosgame.lobbyservice.event.produced;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.lobbyservice.event.BaseEvent;
import nl.fontys.atosgame.lobbyservice.model.Lobby;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LobbyCreatedEvent extends BaseEvent {

    private Lobby lobby;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID gameId;
}
