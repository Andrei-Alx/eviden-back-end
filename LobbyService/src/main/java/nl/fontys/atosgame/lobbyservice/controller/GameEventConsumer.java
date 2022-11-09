package nl.fontys.atosgame.lobbyservice.controller;

import nl.fontys.atosgame.lobbyservice.event.produced.LobbyCreatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.function.Function;

public class GameEventConsumer {

    /**
     * function to consume a GameCreated event
     * input topic: -
     * output topic: game-created-topic
     */
    @Bean
    public Function<?, Message<LobbyCreatedEvent>> consumeGameCreated() {
        return (keyValue) -> {
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
    public Function<?, Message<LobbyCreatedEvent>> consumeGameEnded() {
        return (keyValue) -> {
            // TODO implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }
}
