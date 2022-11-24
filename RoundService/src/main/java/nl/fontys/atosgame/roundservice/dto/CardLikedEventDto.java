package nl.fontys.atosgame.roundservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardLikedEventDto {
    private UUID playerId;
    private UUID gameId;
    private UUID roundId;
    private UUID cardId;
}
