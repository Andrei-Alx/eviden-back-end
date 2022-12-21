package nl.fontys.atosgame.lobbyservice.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinResponseDto {

    private UUID gameId;
    private UUID playerId;
}
