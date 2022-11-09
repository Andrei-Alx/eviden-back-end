package nl.fontys.atosgame.gameservice.controllers;

import nl.fontys.atosgame.gameservice.event.consumed.RoundEndedEvent;
import nl.fontys.atosgame.gameservice.event.consumed.RoundStartedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.function.Function;

/**
 * Collection of consumers for round related events:
 * - RoundStartedEvent
 * - RoundEndedEvent
 * @author Eli
 */
public class RoundConsumers {

    /**
     * Id: C-3
     * Consumer for RoundStartedEvent
     * input topic: round-started-topic
     * output topic: -
     */
    @Bean
    public Function<Message<RoundStartedEvent>, Void> handleRoundStarted() {
        return roundStartedEventMessage -> {
            RoundStartedEvent event = roundStartedEventMessage.getPayload();
            //TODO
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * Id: C-4
     * Consumer for RoundEndedEvent
     * input topic: round-ended-topic
     * output topic: -
     */
    @Bean
    public Function<Message<RoundEndedEvent>, Void> handleRoundEnded() {
        return roundEndedEventMessage -> {
            RoundEndedEvent event = roundEndedEventMessage.getPayload();
            //TODO
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }
}
