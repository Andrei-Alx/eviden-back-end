package nl.fontys.atosgame.lobbyservice.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LobbySettings {

    private UUID id;
    private int maxPlayers;
}
