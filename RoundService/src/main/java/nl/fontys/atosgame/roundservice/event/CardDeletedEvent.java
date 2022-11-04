package nl.fontys.atosgame.roundservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDeletedEvent extends BaseEvent {
    private UUID cardId;
}
