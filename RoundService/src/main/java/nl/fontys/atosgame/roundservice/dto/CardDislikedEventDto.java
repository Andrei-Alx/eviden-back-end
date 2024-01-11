package nl.fontys.atosgame.roundservice.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDislikedEventDto {

    private UUID playerId;

    private UUID gameId;

    private UUID roundId;

    private UUID cardId;
}
