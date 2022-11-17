package nl.fontys.atosgame.roundservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardsDistributedDto {
    private UUID roundId;
    private List<UUID> cardIds;
    private UUID playerId;
    private UUID gameId;
}
