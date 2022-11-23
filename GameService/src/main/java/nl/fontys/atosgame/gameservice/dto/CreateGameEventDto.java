package nl.fontys.atosgame.gameservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.model.LobbySettings;
import nl.fontys.atosgame.gameservice.model.RoundSettings;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGameEventDto {
    private UUID gameId;
    private String title;
    private String companyType;
    private List<RoundSettings> roundSettings;
    private LobbySettings lobbySettings;
}
