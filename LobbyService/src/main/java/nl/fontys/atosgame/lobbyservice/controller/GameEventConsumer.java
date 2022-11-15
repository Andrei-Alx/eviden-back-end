package nl.fontys.atosgame.lobbyservice.controller;

import java.util.function.Function;
import nl.fontys.atosgame.lobbyservice.event.consumed.GameCreatedEvent;
import nl.fontys.atosgame.lobbyservice.event.consumed.GameEndedEvent;
import nl.fontys.atosgame.lobbyservice.event.produced.LobbyCreatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

/**
 * Controller for the game events for the lobby service
 * @author Aniek
 */
@Controller
public class GameEventConsumer {

    /**
     * function to consume a GameCreated event
     * input topic: -
     * output topic: game-created-topic
     */
    @Bean
    public Function<?, Message<GameCreatedEvent>> consumeGameCreated() {
        return keyValue -> {
            // TODO implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * function to consume a GameEnded event
     * input topic: -
     * output topic: game-ended-topic
     */
    @Bean
    public Function<?, Message<GameEndedEvent>> consumeGameEnded() {
        return keyValue -> {
            // TODO implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }
}
