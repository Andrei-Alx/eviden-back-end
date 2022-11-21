package nl.fontys.atosgame.gameappbff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.UUID;

/**
 * Collection of lobby websockets for lobby related sockets:
 * - playerJoined
 * - playerQuit
 * @author Aniek
 */
@Controller
public class LobbySocketController {

    @Autowired
    private SimpMessagingTemplate template;

    /**
     * S-4
     * Send a message to the lobby that a player has joined
     * @param lobbyId, playerId
     * @return
     */
    public void playerJoined(UUID lobbyId, UUID playerId) {
        template.convertAndSend(
            String.format("/socket/gameapp/%s/playerJoined", lobbyId),
            playerId
        );
    }

    /**
     * S-5
     * Send a message to the lobby that a player has quit
     * @param lobbyId, playerId
     * @return
     */
    public void playerQuit(UUID lobbyId, UUID playerId) {
        template.convertAndSend(
            String.format("/socket/gameapp/%s/playerQuit", lobbyId),
            playerId
        );
    }
}
