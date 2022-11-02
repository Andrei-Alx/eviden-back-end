package nl.fontys.atosgame.cardservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.cardservice.model.CardSet;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardSetEventData extends EventData {
    private CardSet cardSet;
}
