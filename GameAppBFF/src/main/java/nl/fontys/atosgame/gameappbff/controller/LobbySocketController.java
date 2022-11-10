package nl.fontys.atosgame.gameappbff.controller;

import nl.fontys.atosgame.gameappbff.dto.CardDislikedDto;
import nl.fontys.atosgame.gameappbff.dto.CardLikedDto;
import nl.fontys.atosgame.gameappbff.dto.PlayerJoinedDto;
import nl.fontys.atosgame.gameappbff.dto.PlayerQuitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class LobbySocketController {

    @Autowired
    private SimpMessagingTemplate template;

    public PlayerJoinedDto playerJoined(String lobbyId, PlayerJoinedDto playerJoinedDto) {
        template.convertAndSend(String.format("/socket/gameapp/%s/playerJoined", lobbyId), playerJoinedDto);
        return playerJoinedDto;
    }

    public PlayerQuitDto playerQuit(String lobbyId, PlayerQuitDto playerQuitDto) {
        template.convertAndSend(String.format("/socket/gameapp/%s/playerQuit", lobbyId), playerQuitDto);
        return playerQuitDto;
    }
}
