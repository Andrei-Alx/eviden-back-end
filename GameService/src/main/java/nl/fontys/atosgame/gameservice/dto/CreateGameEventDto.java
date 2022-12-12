package nl.fontys.atosgame.gameservice.dto;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.model.LobbySettings;
import nl.fontys.atosgame.gameservice.model.RoundSettings;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGameEventDto {

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID gameId;

    private String title;
    private String companyType;
    private List<RoundSettings> roundSettings;
    private LobbySettings lobbySettings;
}
