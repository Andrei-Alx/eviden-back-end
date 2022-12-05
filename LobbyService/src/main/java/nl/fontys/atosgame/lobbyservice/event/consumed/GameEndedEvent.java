package nl.fontys.atosgame.lobbyservice.event.consumed;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.lobbyservice.event.BaseEvent;
import org.hibernate.annotations.Type;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameEndedEvent extends BaseEvent {

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID gameId;
}
