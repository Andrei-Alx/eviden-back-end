package nl.fontys.atosgame.gameappbff.controller;

import java.util.UUID;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private LobbySocketController lobbySocketController;

    @Autowired
    private GameSocketController gameSocketController;

    /*@PostMapping("/testPlayerJoined")
    public String test(@RequestBody UUID lobbyId) {
        PlayerJoined player = new PlayerJoined();
        player.setPlayerId(UUID.randomUUID());
        player.setPlayerName("test");
        lobbySocketController.playerJoined(lobbyId, player);
        return "player joined";
    }*/

    @PostMapping("/testLobbyCreated")
    public String testLobbyCreated(@RequestBody UUID gameId) {
        Lobby lobby = new Lobby();
        gameSocketController.lobby(gameId, lobby);
        return "lobby created";
    }
}
