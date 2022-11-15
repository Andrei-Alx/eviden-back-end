package nl.fontys.atosgame.roundservice.event.produced;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.event.BaseEvent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResultDetermined extends BaseEvent {

    private UUID playerId;
    private UUID gameId;
    private UUID roundId;
    // TODO add result
}
