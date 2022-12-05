package nl.fontys.atosgame.roundservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResultIndeterminedDto {

    private UUID roundId;
    private UUID playerId;
    private UUID gameId;
    private String resultStatus;
}
