package nl.fontys.atosgame.roundservice.controller;

import java.util.function.Function;

import nl.fontys.atosgame.roundservice.dto.CardsDistributedDto;
import nl.fontys.atosgame.roundservice.event.EventFactory;
import nl.fontys.atosgame.roundservice.event.produced.PlayerCardsDistributed;
import nl.fontys.atosgame.roundservice.event.produced.RoundCreatedEvent;
import nl.fontys.atosgame.roundservice.event.produced.RoundCreatedEventKeyValue;
import nl.fontys.atosgame.roundservice.service.CardSetService;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;

/**
 * Collection of all event producers for round events:
 * - RoundCreated
 * - RoundStarted
 * - RoundEnded
 * - PlayerPhaseStarted
 * - PlayerPhaseEnded
 * - PlayerCardsDistributed
 * - PlayerLikedCard
 * - PlayerDislikedCard
 * - PlayerSelectedCards
 * - PlayerResultDetermined
 * - PlayerResultIndeterminate
 * @author Eli
 */
@Controller
public class RoundEventProducers {

    /**
     * Id: P-25
     * function to produce a RoundCreated event
     * input topic: -
     * output topic: round-created-topic
     */
    @Bean
    public Function<RoundCreatedEventKeyValue, Message<RoundCreatedEvent>> produceRoundCreated() {
        return keyValue -> {
            RoundCreatedEvent event = EventFactory.createRoundCreatedEvent(
                "RoundService",
                keyValue.getRound()
            );
            return MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, keyValue.getGameId())
                .build();
        };
    }

    /**
     * Id: P-15
     * function to produce a RoundStarted event
     * input topic: -
     * output topic: round-started-topic
     */
    @Bean
    public Function<?, Message<RoundCreatedEvent>> produceRoundStarted() {
        return keyValue -> {
            // TODO implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * Id: P-16
     * function to produce a RoundEnded event
     * input topic: -
     * output topic: round-ended-topic
     */
    @Bean
    public Function<?, Message<RoundCreatedEvent>> produceRoundEnded() {
        return keyValue -> {
            // TODO implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * Id: P-17
     * function to produce a PlayerPhaseStarted event
     * input topic: -
     * output topic: player-phase-started-topic
     */
    @Bean
    public Function<?, Message<RoundCreatedEvent>> producePlayerPhaseStarted() {
        return keyValue -> {
            // TODO implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * Id: P-18
     * function to produce a PlayerPhaseEnded event
     * input topic: -
     * output topic: player-phase-ended-topic
     */
    @Bean
    public Function<?, Message<RoundCreatedEvent>> producePlayerPhaseEnded() {
        return keyValue -> {
            // TODO implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * Id: P-19
     * function to produce a PlayerCardsDistributed event
     * input topic: -
     * output topic: player-cards-distributed-topic
     */
    @Bean
    public Function<CardsDistributedDto, Message<PlayerCardsDistributed>> producePlayerCardsDistributed() {
        return cardsDistributedDto -> {
            PlayerCardsDistributed event = EventFactory.createPlayerCardsDistributedEvent(
                cardsDistributedDto.getRoundId(),
                cardsDistributedDto.getPlayerId(),
                cardsDistributedDto.getGameId(),
                cardsDistributedDto.getCardIds()
            );
            return MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, cardsDistributedDto.getGameId())
                .build();
        };
    }

    /**
     * Id: P-20
     * function to produce a PlayerLikedCard event
     * input topic: -
     * output topic: player-liked-card-topic
     */
    @Bean
    public Function<?, Message<RoundCreatedEvent>> producePlayerLikedCard() {
        return keyValue -> {
            // TODO implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * Id: P-21
     * function to produce a PlayerDislikedCard event
     * input topic: -
     * output topic: player-disliked-card-topic
     */
    @Bean
    public Function<?, Message<RoundCreatedEvent>> producePlayerDislikedCard() {
        return keyValue -> {
            // TODO implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * Id: P-22
     * function to produce a PlayerSelectedCards event
     * input topic: -
     * output topic: player-selected-cards-topic
     */
    @Bean
    public Function<?, Message<RoundCreatedEvent>> producePlayerSelectedCards() {
        return keyValue -> {
            // TODO implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * Id: P-23
     * function to produce a PlayerResultDetermined event
     * input topic: -
     * output topic: player-result-determined-topic
     */
    @Bean
    public Function<?, Message<RoundCreatedEvent>> producePlayerResultDetermined() {
        return keyValue -> {
            // TODO implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * Id: P-24
     * function to produce a PlayerResultIndeterminate event
     * input topic: -
     * output topic: player-result-indeterminate-topic
     */
    @Bean
    public Function<?, Message<RoundCreatedEvent>> producePlayerResultIndeterminate() {
        return keyValue -> {
            // TODO implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }
}
