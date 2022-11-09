package nl.fontys.atosgame.gameappbff.event.consumed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.event.BaseEvent;
import nl.fontys.atosgame.gameappbff.model.CardSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardSetCreatedEvent extends BaseEvent {
    private CardSet cardSet;
}
