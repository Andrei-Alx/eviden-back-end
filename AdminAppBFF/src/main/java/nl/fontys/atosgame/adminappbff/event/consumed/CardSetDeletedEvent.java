package nl.fontys.atosgame.adminappbff.event.consumed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.adminappbff.event.BaseEvent;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardSetDeletedEvent extends BaseEvent {
    private UUID cardSetId;
}
