package nl.fontys.atosgame.cardservice.event;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDeletedEvent extends BaseEvent {

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID cardId;
}
