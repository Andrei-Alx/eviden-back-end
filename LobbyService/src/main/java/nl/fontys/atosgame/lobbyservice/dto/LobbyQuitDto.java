package nl.fontys.atosgame.lobbyservice.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LobbyQuitDto {

    private UUID lobbyId;
    private UUID playerId;
    private UUID gameId;
}
