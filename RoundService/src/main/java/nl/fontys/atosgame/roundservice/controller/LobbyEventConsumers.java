package nl.fontys.atosgame.roundservice.controller;

import nl.fontys.atosgame.roundservice.event.LobbyCreated;
import nl.fontys.atosgame.roundservice.event.PlayerJoinedEvent;
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
 *  - PlayerQuit
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

    /**
     * function to consume a PlayerJoined event
     * input topic: player-joined-topic
     * output topic: -
     */
    @Bean
    public Function<Message<PlayerJoinedEvent>, Void> handlePlayerJoined() {
        return message -> {
            PlayerJoinedEvent event = message.getPayload();
            lobbyService.addPlayerToLobby(event.getPlayerId(), event.getLobbyId());
            return null;
        };
    }

    /**
     * function to consume a PlayerQuit event
     * input topic: player-quit-topic
     * output topic: -
     */
    @Bean
    public Function<Message<PlayerJoinedEvent>, Void> handlePlayerQuit() {
        return message -> {
            PlayerJoinedEvent event = message.getPayload();
            lobbyService.removePlayerFromLobby(event.getPlayerId(), event.getLobbyId());
            return null;
        };
    }

}
