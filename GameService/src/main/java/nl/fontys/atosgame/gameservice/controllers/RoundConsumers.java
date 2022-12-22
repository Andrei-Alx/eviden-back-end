package nl.fontys.atosgame.gameservice.controllers;

import io.swagger.v3.oas.annotations.info.Contact;
import java.util.function.Function;
import nl.fontys.atosgame.gameservice.event.consumed.RoundCreatedEvent;
import nl.fontys.atosgame.gameservice.event.consumed.RoundEndedEvent;
import nl.fontys.atosgame.gameservice.event.consumed.RoundStartedEvent;
import nl.fontys.atosgame.gameservice.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

/**
 * Collection of consumers for round related events:
 * - RoundStartedEvent
 * - RoundEndedEvent
 * - RoundCreatedEvent
 * @author Eli
 */
@Controller
public class RoundConsumers {

    private RoundService roundService;

    @Autowired
    public RoundConsumers(RoundService roundService) {
        this.roundService = roundService;
    }

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

    /**
     * Id: C-54
     * Consumer for RoundCreatedEvent
     * input topic: round-created-topic
     * output topic: -
     */
    @Bean
    public Function<Message<RoundCreatedEvent>, Void> handleRoundCreated() {
        return roundCreatedEventMessage -> {
            RoundCreatedEvent event = roundCreatedEventMessage.getPayload();
            roundService.createRound(event.getGameId(), event.getRound());
            return null;
        };
    }
}
