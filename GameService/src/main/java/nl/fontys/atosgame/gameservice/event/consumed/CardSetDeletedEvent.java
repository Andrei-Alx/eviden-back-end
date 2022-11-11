package nl.fontys.atosgame.gameservice.event.consumed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.event.BaseEvent;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardSetDeletedEvent extends BaseEvent {
    private UUID cardSetId;
}
