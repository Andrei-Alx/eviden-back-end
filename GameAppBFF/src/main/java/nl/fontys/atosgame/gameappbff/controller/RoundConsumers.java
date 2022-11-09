package nl.fontys.atosgame.gameappbff.controller;

import nl.fontys.atosgame.gameappbff.event.consumed.PlayerPhaseEndedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.PlayerPhaseStartedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.RoundEndedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.RoundStartedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

/**
 * Collection of consumers for round related events:
 * - RoundStartedEvent
 * - RoundEndedEvent
 * - PlayerPhaseStartedEvent
 * - PlayerPhaseEndedEvent
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

    /**
     * Id: C-31
     * Consumer for RoundEndedEvent
     * input topic: round-ended-topic
     * output topic: -
     */
    @Bean
    public Function<Message<RoundEndedEvent>, Void> handleRoundEnded() {
        return message -> {
            //TODO: implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * Id: C-32
     * Consumer for PlayerPhaseStartedEvent
     * input topic: player-phase-started-topic
     * output topic: -
     */
    @Bean
    public Function<Message<PlayerPhaseStartedEvent>, Void> handlePlayerPhaseStarted() {
        return message -> {
            //TODO: implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * Id: C-33
     * Consumer for PlayerPhaseEndedEvent
     * input topic: player-phase-ended-topic
     * output topic: -
     */
    @Bean
    public Function<Message<PlayerPhaseEndedEvent>, Void> handlePlayerPhaseEnded() {
        return message -> {
            //TODO: implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }
}
