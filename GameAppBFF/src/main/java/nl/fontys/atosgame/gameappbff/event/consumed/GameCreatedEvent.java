package nl.fontys.atosgame.gameappbff.event.consumed;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.event.BaseEvent;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameCreatedEvent extends BaseEvent {

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID gameId;
    private String title;
    private String companyType;
}
