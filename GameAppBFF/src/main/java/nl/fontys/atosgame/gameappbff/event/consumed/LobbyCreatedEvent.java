package nl.fontys.atosgame.gameappbff.event.consumed;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.event.BaseEvent;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.model.Round;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LobbyCreatedEvent extends BaseEvent {

    private Lobby lobby;
    private UUID gameId;
}
