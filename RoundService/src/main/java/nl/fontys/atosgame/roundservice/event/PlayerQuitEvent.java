package nl.fontys.atosgame.roundservice.event;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerQuitEvent extends BaseEvent {

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID lobbyId;

    private UUID playerId;
}
