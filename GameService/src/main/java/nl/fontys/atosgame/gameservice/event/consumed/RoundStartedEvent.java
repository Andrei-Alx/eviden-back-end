package nl.fontys.atosgame.gameservice.event.consumed;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.event.BaseEvent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoundStartedEvent extends BaseEvent {

    private UUID roundId;
}
