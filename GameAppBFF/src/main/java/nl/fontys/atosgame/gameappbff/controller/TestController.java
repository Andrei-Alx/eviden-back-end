package nl.fontys.atosgame.gameappbff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private LobbySocketController lobbySocketController;

    @PostMapping("/test")
    public String test() {
        lobbySocketController.playerJoined("Test over websocket");
        return "test";
    }
}
