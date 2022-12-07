package nl.fontys.atosgame.lobbyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.lobbyservice.model.Player;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinResponseDto {
    private UUID gameId;
    private Player player;
}
