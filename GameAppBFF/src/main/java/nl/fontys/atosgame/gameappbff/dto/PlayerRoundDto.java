package nl.fontys.atosgame.gameappbff.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.model.Card;

//Input for body of R-10
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRoundDto {

    private UUID roundId;
    private UUID playerId;
    private UUID gameId;
    private Card[] cards;
}
