package nl.fontys.atosgame.roundservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResultDeterminedDto {

    private UUID playerId;
    private UUID roundId;
    private UUID gameId;
    private ResultDto result;

}
