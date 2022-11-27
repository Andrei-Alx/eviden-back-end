package nl.fontys.atosgame.gameappbff.event.consumed;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.event.BaseEvent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardSetDeletedEvent extends BaseEvent {

    private UUID cardSetId;
}
