package nl.fontys.atosgame.gameservice.event.consumed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.event.BaseEvent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoundEndedEvent extends BaseEvent {
    private String roundId;
}
