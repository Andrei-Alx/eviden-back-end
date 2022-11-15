package nl.fontys.atosgame.adminappbff.event.consumed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.adminappbff.event.BaseEvent;
import nl.fontys.atosgame.adminappbff.model.CardSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardSetCreatedEvent extends BaseEvent {

    private CardSet cardSet;
}
