package nl.fontys.atosgame.adminappbff.event.consumed;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.adminappbff.event.BaseEvent;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardSetDeletedEvent extends BaseEvent {

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID cardSetId;
}
