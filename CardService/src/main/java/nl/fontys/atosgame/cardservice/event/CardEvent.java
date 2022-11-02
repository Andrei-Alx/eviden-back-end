package nl.fontys.atosgame.cardservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.cardservice.model.Card;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardEvent extends BaseEvent {
    private Card card;
}
