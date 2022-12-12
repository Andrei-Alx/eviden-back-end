package nl.fontys.atosgame.roundservice.event.produced;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.dto.ResultDto;
import nl.fontys.atosgame.roundservice.event.BaseEvent;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResultDeterminedEvent extends BaseEvent {

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID playerId;
    private UUID gameId;
    private UUID roundId;
    private ResultDto result;
}
