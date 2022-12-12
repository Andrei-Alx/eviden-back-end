package nl.fontys.atosgame.lobbyservice.event.produced;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.lobbyservice.event.BaseEvent;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerQuitEvent extends BaseEvent {

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID lobbyId;

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID playerId;

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID gameId;
}
