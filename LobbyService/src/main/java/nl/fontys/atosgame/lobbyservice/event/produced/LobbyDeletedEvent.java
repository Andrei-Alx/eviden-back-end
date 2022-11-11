package nl.fontys.atosgame.lobbyservice.event.produced;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.lobbyservice.event.BaseEvent;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LobbyDeletedEvent extends BaseEvent {
    private UUID lobbyId;
    private UUID gameId;
}
