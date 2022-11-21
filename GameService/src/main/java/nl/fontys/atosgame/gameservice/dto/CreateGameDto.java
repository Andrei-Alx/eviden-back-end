package nl.fontys.atosgame.gameservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.model.LobbySettings;
import nl.fontys.atosgame.gameservice.model.RoundSettings;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGameDto {
    private String companyType;
    private LobbySettings lobbySettings;
    private List<RoundSettings> roundSettings;
}
