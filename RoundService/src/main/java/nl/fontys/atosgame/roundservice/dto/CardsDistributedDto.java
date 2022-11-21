package nl.fontys.atosgame.roundservice.dto;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardsDistributedDto {

    private UUID roundId;
    private List<UUID> cardIds;
    private UUID playerId;
    private UUID gameId;
}
