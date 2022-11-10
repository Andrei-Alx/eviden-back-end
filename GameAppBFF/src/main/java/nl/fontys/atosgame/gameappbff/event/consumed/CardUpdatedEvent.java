package nl.fontys.atosgame.gameappbff.event.consumed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.event.BaseEvent;
import nl.fontys.atosgame.gameappbff.model.Card;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardUpdatedEvent extends BaseEvent {
    private Card card;
}
