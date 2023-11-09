package nl.fontys.atosgame.gameservice.controllers;

import java.util.function.Function;
import nl.fontys.atosgame.gameservice.event.consumed.LobbyCreatedEvent;
import nl.fontys.atosgame.gameservice.model.Lobby;
import nl.fontys.atosgame.gameservice.service.LobbyService;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

/**
 * Consumers for lobby events:
 * - LobbyCreatedEvent
 * @author Eli
 */
@Controller
public class LobbyConsumers {

    private final LobbyService lobbyService;

    public LobbyConsumers(LobbyService lobbyService)
    {
        this.lobbyService = lobbyService;
    }
    /**
     * Id: C-57
     * Consumer for LobbyCreatedEvent
     * input topic: lobby-created-topic
     * output topic: -
     */
    @Bean
    public Function<Message<LobbyCreatedEvent>, Void> handleLobbyCreated() {
        return lobbyCreatedEventMessage -> {
            LobbyCreatedEvent event = lobbyCreatedEventMessage.getPayload();
            lobbyService.AddLobby(event.getLobby());
            return null;
        };
    }
}
