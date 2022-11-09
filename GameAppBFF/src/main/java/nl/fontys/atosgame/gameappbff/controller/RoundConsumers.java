package nl.fontys.atosgame.gameappbff.controller;

import nl.fontys.atosgame.gameappbff.event.consumed.*;
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
 * - PlayerCardsDistributedEvent
 * - PlayerLikedCardEvent
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

    /**
     * Id: C-34
     * Consumer for PlayerCardsDistributedEvent
     * input topic: player-cards-distributed-topic
     * output topic: -
     */
    @Bean
    public Function<Message<PlayerCardsDistributedEvent>, Void> handlePlayerCardsDistributed() {
        return message -> {
            //TODO: implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * Id: C-35
     * Consumer for PlayerLikedCardEvent
     * input topic: player-liked-card-topic
     * output topic: -
     */
    @Bean
    public Function<Message<PlayerLikedCardEvent>, Void> handlePlayerLikedCard() {
        return message -> {
            //TODO: implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }
}
