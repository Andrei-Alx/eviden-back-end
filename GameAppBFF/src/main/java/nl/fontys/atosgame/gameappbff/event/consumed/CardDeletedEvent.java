package nl.fontys.atosgame.gameappbff.event.consumed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.event.BaseEvent;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDeletedEvent extends BaseEvent {
    private UUID cardid;
}
