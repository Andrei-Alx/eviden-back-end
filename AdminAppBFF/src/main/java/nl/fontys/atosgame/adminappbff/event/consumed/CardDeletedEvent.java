package nl.fontys.atosgame.adminappbff.event.consumed;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.adminappbff.event.BaseEvent;
import nl.fontys.atosgame.adminappbff.model.Card;
import org.hibernate.annotations.Type;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDeletedEvent extends BaseEvent {

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID cardid;
}
