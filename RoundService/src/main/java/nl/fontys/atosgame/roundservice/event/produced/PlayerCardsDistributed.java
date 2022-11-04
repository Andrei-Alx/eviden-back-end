package nl.fontys.atosgame.roundservice.event.produced;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.event.BaseEvent;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerCardsDistributed extends BaseEvent {
    private UUID roundId;
    private List<UUID> cardIds;
    private UUID playerId;
    private UUID gameId;
}
