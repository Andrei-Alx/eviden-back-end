package nl.fontys.atosgame.gameservice.event.consumed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.event.BaseEvent;
import nl.fontys.atosgame.gameservice.model.CardSet;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardSetEvent extends BaseEvent {

    private List<CardSet> cardSets;
}
