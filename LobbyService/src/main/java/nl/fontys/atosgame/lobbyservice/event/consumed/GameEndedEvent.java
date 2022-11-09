package nl.fontys.atosgame.lobbyservice.event.consumed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.lobbyservice.event.BaseEvent;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameEndedEvent extends BaseEvent {
    private UUID gameId;
}
