package nl.fontys.atosgame.roundservice.dto;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardSubmitRequestDto {

    private UUID roundId;
    private UUID playerId;
    private List<UUID> cardIds;
}
