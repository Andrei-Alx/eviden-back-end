package nl.fontys.atosgame.roundservice.dto;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardsSelectedEventDto {

    private UUID playerId;

    private List<UUID> cardIds;

    private UUID roundId;

    private UUID gameId;
}
