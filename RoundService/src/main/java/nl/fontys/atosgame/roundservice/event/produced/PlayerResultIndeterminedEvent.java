package nl.fontys.atosgame.roundservice.event.produced;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.event.BaseEvent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResultIndeterminedEvent extends BaseEvent {

    private UUID roundId;
    private UUID playerId;
    private UUID gameId;
    private String resultStatus;
}
