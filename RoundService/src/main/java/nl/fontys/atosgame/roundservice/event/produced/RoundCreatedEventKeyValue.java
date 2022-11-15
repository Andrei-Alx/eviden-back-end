package nl.fontys.atosgame.roundservice.event.produced;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import nl.fontys.atosgame.roundservice.model.Round;

@AllArgsConstructor
@Data
public class RoundCreatedEventKeyValue {

    private UUID gameId;
    private Round round;
}
