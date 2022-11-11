package nl.fontys.atosgame.gameappbff.controller;
import nl.fontys.atosgame.gameappbff.dto.PlayerJoinedDto;
import nl.fontys.atosgame.gameappbff.dto.PlayerQuitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

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
     * @param playerJoinedDto
     * @return
     */
    public PlayerJoinedDto playerJoined(String lobbyId, PlayerJoinedDto playerJoinedDto) {
        //TODO
        template.convertAndSend(String.format("/socket/gameapp/%s/playerJoined", lobbyId), playerJoinedDto);
        return playerJoinedDto;
    }

    /**
     * S-5
     * Send a message to the lobby that a player has quit
     * @param playerQuitDto
     * @return
     */
    public PlayerQuitDto playerQuit(String lobbyId, PlayerQuitDto playerQuitDto) {
        //TODO
        template.convertAndSend(String.format("/socket/gameapp/%s/playerQuit", lobbyId), playerQuitDto);
        return playerQuitDto;
    }
}
