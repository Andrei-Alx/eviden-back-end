package nl.fontys.atosgame.lobbyservice.model;

import java.util.UUID;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LobbySettings {

    private UUID id;
    private int maxPlayers;
}
