package nl.fontys.atosgame.gameappbff.dto;

import java.util.Collection;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.model.Card;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardsDto {

    private UUID playerId;
    private Collection<Card> cards;
}
