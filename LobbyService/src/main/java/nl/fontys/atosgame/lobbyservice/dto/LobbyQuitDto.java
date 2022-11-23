package nl.fontys.atosgame.lobbyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LobbyQuitDto {

    private UUID lobbyId;
    private UUID playerId;
    private UUID gameId;
}
