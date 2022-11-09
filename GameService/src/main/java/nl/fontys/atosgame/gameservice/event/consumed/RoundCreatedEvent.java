package nl.fontys.atosgame.gameservice.event.consumed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.event.BaseEvent;
import nl.fontys.atosgame.gameservice.model.Round;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoundCreatedEvent extends BaseEvent {
    private Round round;
    private UUID gameId;
}
