package nl.fontys.atosgame.lobbyservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEvent {
    private UUID id;
    private LocalDateTime timestamp ;
    private String type;
    private String service;
}
