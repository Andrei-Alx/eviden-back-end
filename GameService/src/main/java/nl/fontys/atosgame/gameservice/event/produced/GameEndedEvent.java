package nl.fontys.atosgame.gameservice.event.produced;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.event.BaseEvent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameEndedEvent extends BaseEvent {

    private UUID gameId;
}
