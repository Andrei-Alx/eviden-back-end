package nl.fontys.atosgame.gameappbff.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.model.Card;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDislikedDto {
    private UUID playerId;
    private Card card;
}
