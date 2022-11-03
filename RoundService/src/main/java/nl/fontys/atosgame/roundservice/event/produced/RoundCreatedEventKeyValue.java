package nl.fontys.atosgame.roundservice.event.produced;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.fontys.atosgame.roundservice.model.Round;

import java.util.UUID;

@AllArgsConstructor
@Data
public class RoundCreatedEventKeyValue {
    private UUID gameId;
    private Round round;
}
