package nl.fontys.atosgame.roundservice.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResultDeterminedDto {

    private UUID playerId;
    private UUID roundId;
    private UUID gameId;
    private ResultDto result;
}
