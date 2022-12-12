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
public class RoundEndedEvent extends BaseEvent {

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID roundId;

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID gameId;
}
