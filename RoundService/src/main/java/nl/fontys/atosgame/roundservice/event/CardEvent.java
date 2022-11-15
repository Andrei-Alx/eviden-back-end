package nl.fontys.atosgame.roundservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.model.Card;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardEvent extends BaseEvent {

    private Card card;
}
