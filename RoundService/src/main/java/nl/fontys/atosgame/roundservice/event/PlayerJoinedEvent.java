package nl.fontys.atosgame.roundservice.event;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerJoinedEvent extends BaseEvent {

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID lobbyId;

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID playerId;
}
