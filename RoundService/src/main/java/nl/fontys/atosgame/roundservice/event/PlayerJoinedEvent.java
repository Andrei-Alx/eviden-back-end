package nl.fontys.atosgame.roundservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
}
