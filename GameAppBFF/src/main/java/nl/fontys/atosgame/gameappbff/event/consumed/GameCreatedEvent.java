package nl.fontys.atosgame.gameappbff.event.consumed;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.event.BaseEvent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameCreatedEvent extends BaseEvent {

    private UUID gameId;

    private String title;
    private String companyType;
}
