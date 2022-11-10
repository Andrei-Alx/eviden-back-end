package nl.fontys.atosgame.gameappbff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class LobbySocketController {

    @Autowired
    private SimpMessagingTemplate template;

    public String playerJoined(String lobbyId, String message) {
        template.convertAndSend(String.format("/socket/gameapp/%s/playerJoined", lobbyId), message);
        return message;
    }
}
