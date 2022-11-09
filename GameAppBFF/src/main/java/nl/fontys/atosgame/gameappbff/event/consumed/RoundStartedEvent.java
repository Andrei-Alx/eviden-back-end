package nl.fontys.atosgame.gameappbff.event.consumed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoundStartedEvent {
    private UUID roundId;
    private UUID gameId;
}
