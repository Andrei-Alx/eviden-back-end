package nl.fontys.atosgame.gameappbff.controller;

import java.util.UUID;
import nl.fontys.atosgame.gameappbff.dto.PlayerJoinedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private LobbySocketController lobbySocketController;

    @PostMapping("/test")
    public String test(@RequestBody String lobbyId) {
        PlayerJoinedDto dto = new PlayerJoinedDto();
        dto.setPlayerId(UUID.randomUUID());
        dto.setPlayerName("TestPlayer");
        lobbySocketController.playerJoined(lobbyId, dto);
        return lobbyId;
    }
}
