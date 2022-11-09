package nl.fontys.atosgame.gameservice.event.produced;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.event.BaseEvent;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameStartedEvent extends BaseEvent {
    private UUID gameId;
}
