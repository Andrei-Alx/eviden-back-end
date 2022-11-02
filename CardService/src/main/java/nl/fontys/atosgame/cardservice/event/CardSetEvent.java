package nl.fontys.atosgame.cardservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.cardservice.model.CardSet;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardSetEvent extends BaseEvent {
    private CardSet cardSet;
}
