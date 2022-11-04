package nl.fontys.atosgame.roundservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardSetDeletedEvent extends BaseEvent {
    private UUID cardSetId;
}
