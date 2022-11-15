package nl.fontys.atosgame.lobbyservice.controller;

import java.util.function.Function;
import nl.fontys.atosgame.lobbyservice.event.consumed.GameCreatedEvent;
import nl.fontys.atosgame.lobbyservice.event.consumed.GameEndedEvent;
import nl.fontys.atosgame.lobbyservice.event.produced.LobbyCreatedEvent;
import nl.fontys.atosgame.lobbyservice.service.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

/**
 * Controller for the game events for the lobby service
 * @author Aniek, Eli
 */
@Controller
public class GameEventConsumer {

    private final LobbyService lobbyService;

    public GameEventConsumer(@Autowired LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    /**
     * Id: C-1
     * function to consume a GameCreated event
     * input topic: game-created-topic
     * output topic: -
     */
    @Bean
    public Function<Message<GameCreatedEvent>, Void> handleGameCreated() {
        return keyValue -> {
            GameCreatedEvent event = keyValue.getPayload();
            lobbyService.createLobby(event.getLobbySettings(), event.getGameId());
            return null;
        };
    }

    /**
     * Id: C-2
     * function to consume a GameEnded event
     * input topic: game-ended-topic
     * output topic: -
     */
    @Bean
    public Function<Message<GameEndedEvent>, Void> handleGameEnded() {
        return keyValue -> {
            GameEndedEvent event = keyValue.getPayload();
            lobbyService.deleteLobbyByGameId(event.getGameId());
            return null;
        };
    }
}
