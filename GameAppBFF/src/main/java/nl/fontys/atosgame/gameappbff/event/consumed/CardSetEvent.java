package nl.fontys.atosgame.gameappbff.event.consumed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.event.BaseEvent;
import nl.fontys.atosgame.gameappbff.model.CardSet;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardSetEvent extends BaseEvent {
    List<CardSet> cardSets;
}
