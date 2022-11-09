package nl.fontys.atosgame.gameappbff.controller;

import nl.fontys.atosgame.gameappbff.event.consumed.GameCreatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

/**
 * Collection of consumers for game related events:
 * - GameCreatedEvent
 * @author Eli
 */
@Controller
public class GameConsumers {

    /**
     * Id: C-26
     * Consumer for GameCreatedEvent
     * input topic: game-created-topic
     * output topic: -
     */
    @Bean
    public Function<Message<GameCreatedEvent>, Void> handleGameCreated() {
        return message -> {
            // TODO: implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }
}
