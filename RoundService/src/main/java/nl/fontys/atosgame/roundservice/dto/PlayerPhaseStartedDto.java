package nl.fontys.atosgame.roundservice.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerPhaseStartedDto {

    private int phaseNumber;
     
    private UUID playerId;
     
    private UUID gameId;
     
    private UUID roundId;
}
