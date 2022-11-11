package nl.fontys.atosgame.adminappbff.event.consumed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.adminappbff.event.BaseEvent;
import nl.fontys.atosgame.adminappbff.model.Card;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardCreatedEvent extends BaseEvent {
    private Card card;
}
