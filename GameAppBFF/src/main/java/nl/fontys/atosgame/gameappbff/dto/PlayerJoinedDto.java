package nl.fontys.atosgame.gameappbff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerJoinedDto {
    private UUID playerId;
    private String playerName;
}
