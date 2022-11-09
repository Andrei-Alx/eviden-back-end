package nl.fontys.atosgame.lobbyservice.event.produced;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.lobbyservice.event.BaseEvent;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerJoinedEvent extends BaseEvent {
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID lobbyId;
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID playerId;
    private UUID gameId;
}
