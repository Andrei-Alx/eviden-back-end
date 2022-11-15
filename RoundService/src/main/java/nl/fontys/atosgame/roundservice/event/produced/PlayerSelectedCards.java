package nl.fontys.atosgame.roundservice.event.produced;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.event.BaseEvent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerSelectedCards extends BaseEvent {

    private UUID gameId;
    private UUID roundId;
    private UUID playerId;
    private List<UUID> cardIds;
}
