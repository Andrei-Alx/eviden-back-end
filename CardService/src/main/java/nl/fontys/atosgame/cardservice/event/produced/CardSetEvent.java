package nl.fontys.atosgame.cardservice.event.produced;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.cardservice.event.BaseEvent;
import nl.fontys.atosgame.cardservice.model.CardSet;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardSetEvent extends BaseEvent {
    List<CardSet> cardSets;
}
