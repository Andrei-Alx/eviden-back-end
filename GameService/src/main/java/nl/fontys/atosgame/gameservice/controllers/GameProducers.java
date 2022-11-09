package nl.fontys.atosgame.gameservice.controllers;

import nl.fontys.atosgame.gameservice.event.produced.GameCreatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

/**
 * Collection of producers for game related events:
 * - GameCreatedEvent
 * @author Eli
 */
@Controller
public class GameProducers {

    /**
     * Id: P-11
     * Producer for GameCreatedEvent
     * input topic: -
     * output topic: game-created-topic
     */
    @Bean
    public Function<?, Message<GameCreatedEvent>> produceGameCreated() {
        return input -> {
            //TODO
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }
}
