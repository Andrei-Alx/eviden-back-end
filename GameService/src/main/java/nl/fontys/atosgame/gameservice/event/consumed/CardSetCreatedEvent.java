package nl.fontys.atosgame.gameservice.event.consumed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.event.BaseEvent;
import nl.fontys.atosgame.gameservice.model.CardSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardSetCreatedEvent extends BaseEvent {

    private CardSet cardSet;
}
