package nl.fontys.atosgame.roundservice.dto.event.produced;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.dto.event.BaseEvent;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerJoinedEvent extends BaseEvent {
    private UUID playerId;
    private UUID lobbyId;
    private UUID gameId;
}
