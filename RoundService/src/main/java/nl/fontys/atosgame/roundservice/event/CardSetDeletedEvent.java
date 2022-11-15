package nl.fontys.atosgame.roundservice.event;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardSetDeletedEvent extends BaseEvent {

    private UUID cardSetId;
}
