package nl.fontys.atosgame.adminappbff.event;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEvent {

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    private LocalDateTime timestamp;
    private String type;
    private String service;
}
