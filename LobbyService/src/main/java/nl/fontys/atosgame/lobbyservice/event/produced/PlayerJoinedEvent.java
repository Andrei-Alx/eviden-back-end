package nl.fontys.atosgame.lobbyservice.event.produced;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.lobbyservice.event.BaseEvent;
import nl.fontys.atosgame.lobbyservice.model.Player;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerJoinedEvent extends BaseEvent {

    private UUID lobbyId;

    private Player player;

    private UUID gameId;
}
