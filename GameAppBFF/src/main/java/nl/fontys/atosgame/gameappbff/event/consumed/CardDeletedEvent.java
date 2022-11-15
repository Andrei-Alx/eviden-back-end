package nl.fontys.atosgame.gameappbff.event.consumed;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.event.BaseEvent;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDeletedEvent extends BaseEvent {

    private UUID cardId;
}
