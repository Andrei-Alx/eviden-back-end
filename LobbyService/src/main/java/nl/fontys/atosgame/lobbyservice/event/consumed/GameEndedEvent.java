package nl.fontys.atosgame.lobbyservice.event.consumed;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.lobbyservice.event.BaseEvent;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameEndedEvent extends BaseEvent {

    private UUID gameId;
}
