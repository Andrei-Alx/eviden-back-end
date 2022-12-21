package nl.fontys.atosgame.gameappbff.event.consumed;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.enums.ResultStatus;
import nl.fontys.atosgame.gameappbff.event.BaseEvent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResultIndeterminateEvent extends BaseEvent {

    private UUID roundId;
    private UUID playerId;
    private UUID gameId;
    private ResultStatus resultStatus;
}
