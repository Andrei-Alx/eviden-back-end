package nl.fontys.atosgame.lobbyservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LobbySettings {
    private UUID id;
    private int maxPlayers;
}
