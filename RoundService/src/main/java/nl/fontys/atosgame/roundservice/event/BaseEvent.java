package nl.fontys.atosgame.roundservice.event;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEvent {

    private UUID id;
    private LocalDateTime timestamp;
    private String type;
    private String service;
}
