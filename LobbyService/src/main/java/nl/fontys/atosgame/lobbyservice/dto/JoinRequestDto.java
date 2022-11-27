package nl.fontys.atosgame.lobbyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinRequestDto {

    private String lobbyCode;
    private String playerName;
}
