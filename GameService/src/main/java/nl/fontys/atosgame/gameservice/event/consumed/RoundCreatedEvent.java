package nl.fontys.atosgame.gameservice.event.consumed;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.event.BaseEvent;
import nl.fontys.atosgame.gameservice.model.Round;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoundCreatedEvent extends BaseEvent {

    private Round round;
    private UUID gameId;
}
