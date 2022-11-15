package nl.fontys.atosgame.gameservice.controllers;

import java.util.function.Function;
import nl.fontys.atosgame.gameservice.event.consumed.LobbyCreatedEvent;
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

    /**
     * Id: C-55
     * Consumer for LobbyCreatedEvent
     * input topic: lobby-created-topic
     * output topic: -
     */
    @Bean
    public Function<Message<LobbyCreatedEvent>, Void> handleLobbyCreated() {
        return lobbyCreatedEventMessage -> {
            LobbyCreatedEvent event = lobbyCreatedEventMessage.getPayload();
            //TODO
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }
}
