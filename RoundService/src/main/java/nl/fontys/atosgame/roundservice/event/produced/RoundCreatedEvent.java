package nl.fontys.atosgame.roundservice.event.produced;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.event.BaseEvent;
import nl.fontys.atosgame.roundservice.model.Round;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoundCreatedEvent extends BaseEvent {

    private Round round;
    private UUID gameId;
}
