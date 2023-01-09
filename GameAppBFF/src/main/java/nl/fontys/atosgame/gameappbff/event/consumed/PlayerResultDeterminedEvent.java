package nl.fontys.atosgame.gameappbff.event.consumed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.dto.ResultDto;
import nl.fontys.atosgame.gameappbff.event.BaseEvent;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResultDeterminedEvent extends BaseEvent {

    private UUID playerId;
    private UUID roundId;
    private UUID gameId;
    private ResultDto result;
}
