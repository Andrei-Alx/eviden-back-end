package nl.fontys.atosgame.roundservice.dto.event.produced;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.dto.event.BaseEvent;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerQuitEvent extends BaseEvent {
    private UUID lobbyId;
    private UUID playerId;
    private UUID gameId;
}
