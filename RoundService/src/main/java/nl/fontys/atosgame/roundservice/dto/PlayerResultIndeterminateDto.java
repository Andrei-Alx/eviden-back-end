package nl.fontys.atosgame.roundservice.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.enums.ResultStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResultIndeterminateDto {

    private UUID roundId;
    private UUID playerId;
    private UUID gameId;
    private ResultStatus resultStatus;
}
