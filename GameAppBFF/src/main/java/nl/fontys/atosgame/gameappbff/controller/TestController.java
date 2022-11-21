package nl.fontys.atosgame.gameappbff.controller;

import nl.fontys.atosgame.gameappbff.dto.PlayerJoinedDto;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {

    @Autowired
    private LobbySocketController lobbySocketController;

    @Autowired
    private GameSocketController gameSocketController;

    @PostMapping("/test")
    public String test(@RequestBody String lobbyId) {
        PlayerJoinedDto dto = new PlayerJoinedDto();
        dto.setPlayerId(UUID.randomUUID());
        dto.setPlayerName("TestPlayer");
        lobbySocketController.playerJoined(lobbyId,dto);
        return lobbyId;
    }

    @PostMapping("/testLobbyCreated")
    public String testLobbyCreated(@RequestBody UUID gameId) {
        Lobby lobby = new Lobby();
        gameSocketController.lobby(gameId, lobby);
        return "lobby created";
    }

}
