package nl.fontys.atosgame.cardservice.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEvent {
    private UUID id;
    private LocalDateTime timestamp;
    private String type;
    private String service;
    private EventData data;
}
