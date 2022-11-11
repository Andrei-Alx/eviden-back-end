package nl.fontys.atosgame.gameservice.controllers;

import nl.fontys.atosgame.gameservice.event.consumed.LobbyCreatedEvent;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

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
    public Function<Message<LobbyCreatedEvent>, Void> handleLobbyCreated() {
        return lobbyCreatedEventMessage -> {
            LobbyCreatedEvent event = lobbyCreatedEventMessage.getPayload();
            //TODO
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }
}
