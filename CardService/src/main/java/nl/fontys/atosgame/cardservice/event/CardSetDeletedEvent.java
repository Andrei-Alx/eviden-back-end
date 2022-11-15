package nl.fontys.atosgame.cardservice.event;

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
