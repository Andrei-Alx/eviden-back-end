package nl.fontys.atosgame.gameappbff.event.consumed;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.event.BaseEvent;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerPhaseEndedEvent extends BaseEvent {

    private int phaseNumber;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID playerId;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID gameId;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID roundId;
}
