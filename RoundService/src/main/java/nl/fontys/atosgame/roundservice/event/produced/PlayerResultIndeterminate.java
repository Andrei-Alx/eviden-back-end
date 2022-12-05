package nl.fontys.atosgame.roundservice.event.produced;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.event.BaseEvent;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResultIndeterminate extends BaseEvent {

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID gameId;

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID roundId;

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID playerId;
}
