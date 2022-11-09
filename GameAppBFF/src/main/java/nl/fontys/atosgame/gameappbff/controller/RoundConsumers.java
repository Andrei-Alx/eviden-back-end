package nl.fontys.atosgame.gameappbff.controller;

import nl.fontys.atosgame.gameappbff.event.consumed.RoundStartedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

/**
 * Collection of consumers for round related events:
 * - RoundStartedEvent
 */
@Controller
public class RoundConsumers {

    /**
     * Id: C-30
     * Consumer for RoundStartedEvent
     * input topic: round-started-topic
     * output topic: -
     */
    @Bean
    public Function<Message<RoundStartedEvent>, Void> handleRoundStarted() {
        return message -> {
            //TODO: implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }
}
