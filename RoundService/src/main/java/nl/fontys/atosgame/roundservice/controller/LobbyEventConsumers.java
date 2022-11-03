package nl.fontys.atosgame.roundservice.controller;

import nl.fontys.atosgame.roundservice.event.LobbyCreated;
import nl.fontys.atosgame.roundservice.service.GameService;
import nl.fontys.atosgame.roundservice.service.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

/**
 *  Collection of all event consumers for lobby events:
 *  - LobbyCreated
 *  - PlayerJoined
 *
 *  @author Eli
 */
@Controller
public class LobbyEventConsumers {

    private GameService gameService;
    private LobbyService lobbyService;

    public LobbyEventConsumers(@Autowired GameService gameService, @Autowired LobbyService lobbyService) {
        this.gameService = gameService;
        this.lobbyService = lobbyService;
    }

    /**
     * function to consume a LobbyCreated event
     * input topic: lobby-created-topic
     * output topic: -
     */
    @Bean
    public Function<Message<LobbyCreated>, Void> handleLobbyCreated() {
        return message -> {
            LobbyCreated event = message.getPayload();
            lobbyService.createLobby(event.getLobby());
            gameService.addLobbyToGame(event.getGameId(), event.getLobby());
            return null;
        };
    }
}
