package nl.fontys.atosgame.roundservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.model.CardSet;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardSetEvent extends BaseEvent {

    private CardSet cardSet;
}
