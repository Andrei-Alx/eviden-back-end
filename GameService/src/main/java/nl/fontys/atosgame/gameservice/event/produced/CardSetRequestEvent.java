package nl.fontys.atosgame.gameservice.event.produced;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.event.BaseEvent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardSetRequestEvent extends BaseEvent {
    String dummy;
}
