package nl.fontys.atosgame.gameappbff.controller;

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
        lobbySocketController.playerJoined(lobbyId,"Test over websocket");
        return "test";
    }
}
