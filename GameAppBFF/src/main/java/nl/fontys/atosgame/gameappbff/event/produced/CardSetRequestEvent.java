package nl.fontys.atosgame.gameappbff.event.produced;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.event.BaseEvent;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardSetRequestEvent extends BaseEvent {
    String dummy;
}
