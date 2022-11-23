package nl.fontys.atosgame.gameappbff.event.consumed;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.event.BaseEvent;
import nl.fontys.atosgame.gameappbff.model.PlayerJoined;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerJoinedEvent extends BaseEvent {

    private UUID lobbyId;
    private PlayerJoined player;
    private UUID gameId;
}
