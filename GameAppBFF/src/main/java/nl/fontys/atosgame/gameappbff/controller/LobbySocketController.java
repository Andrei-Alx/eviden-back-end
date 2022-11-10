package nl.fontys.atosgame.gameappbff.controller;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LobbySocketController {

    @SendTo("/socket/gameapp/lobby/playerJoined")
    public String playerJoined(String message) {
        return message;
    }
}
