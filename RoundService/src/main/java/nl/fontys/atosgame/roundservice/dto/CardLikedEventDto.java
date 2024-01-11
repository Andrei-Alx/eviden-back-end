package nl.fontys.atosgame.roundservice.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardLikedEventDto {

    private UUID playerId;

    private UUID gameId;

    private UUID roundId;

    private UUID cardId;
}
