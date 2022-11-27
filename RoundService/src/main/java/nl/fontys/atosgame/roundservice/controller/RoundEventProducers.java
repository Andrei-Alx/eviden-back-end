package nl.fontys.atosgame.roundservice.controller;

import java.util.function.Function;

import nl.fontys.atosgame.roundservice.dto.*;
import nl.fontys.atosgame.roundservice.event.EventFactory;
import nl.fontys.atosgame.roundservice.event.produced.*;
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
                keyValue.getRound(),
                keyValue.getGameId()
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
    public Function<RoundStartedDto, Message<RoundStartedEvent>> produceRoundStarted() {
        return round -> {
            RoundStartedEvent event = EventFactory.createRoundStartedEvent(
                round.getRoundId(),
                round.getGameId()
            );

            return MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, round.getGameId())
                .build();
        };
    }

    /**
     * Id: P-16
     * function to produce a RoundEnded event
     * input topic: -
     * output topic: round-ended-topic
     */
    @Bean
    public Function<RoundEndedDto, Message<RoundEndedEvent>> produceRoundEnded() {
        return dto -> {
            RoundEndedEvent event = EventFactory.createRoundEndedEvent(
                dto.getRoundId(),
                dto.getGameId()
            );
            return MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, dto.getGameId())
                .build();
        };
    }

    /**
     * Id: P-17
     * function to produce a PlayerPhaseStarted event
     * input topic: -
     * output topic: player-phase-started-topic
     */
    @Bean
    public Function<PlayerPhaseStartedDto, Message<PlayerPhaseStartedEvent>> producePlayerPhaseStarted() {
        return playerPhaseStartedDto -> {
            PlayerPhaseStartedEvent event = EventFactory.createPlayerPhaseStartedEvent(
                playerPhaseStartedDto.getPhaseNumber(),
                playerPhaseStartedDto.getRoundId(),
                playerPhaseStartedDto.getGameId(),
                playerPhaseStartedDto.getPlayerId()
            );
            return MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, playerPhaseStartedDto.getGameId())
                .build();
        };
    }

    /**
     * Id: P-18
     * function to produce a PlayerPhaseEnded event
     * input topic: -
     * output topic: player-phase-ended-topic
     */
    @Bean
    public Function<PlayerPhaseEndedDto, Message<PlayerPhaseEndedEvent>> producePlayerPhaseEnded() {
        return dto -> {
            PlayerPhaseEndedEvent event = EventFactory.createPlayerPhaseEndedEvent(
                dto.getPhaseNumber(),
                dto.getRoundId(),
                dto.getGameId(),
                dto.getPlayerId()
            );
            return MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, dto.getGameId())
                .build();
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
    public Function<CardLikedEventDto, Message<PlayerLikedCard>> producePlayerLikedCard() {
        return keyValue -> {
            PlayerLikedCard event = EventFactory.createPlayerLikedCardEvent(
                keyValue.getRoundId(),
                keyValue.getGameId(),
                keyValue.getPlayerId(),
                keyValue.getCardId()
            );

            return MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, keyValue.getGameId())
                .build();
        };
    }

    /**
     * Id: P-21
     * function to produce a PlayerDislikedCard event
     * input topic: -
     * output topic: player-disliked-card-topic
     */
    @Bean
    public Function<CardDislikedEventDto, Message<PlayerDislikedCard>> producePlayerDislikedCard() {
        return keyValue -> {
            PlayerDislikedCard event = EventFactory.createPlayerDislikedCardEvent(
                keyValue.getRoundId(),
                keyValue.getGameId(),
                keyValue.getPlayerId(),
                keyValue.getCardId()
            );
            return MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, keyValue.getGameId())
                .build();
        };
    }

    /**
     * Id: P-22
     * function to produce a PlayerSelectedCards event
     * input topic: -
     * output topic: player-selected-cards-topic
     */
    @Bean
    public Function<CardsSelectedEventDto, Message<PlayerSelectedCards>> producePlayerSelectedCards() {
        return keyValue -> {
            PlayerSelectedCards event = EventFactory.createPlayerSelectedCardsEvent(
                keyValue.getRoundId(),
                keyValue.getGameId(),
                keyValue.getPlayerId(),
                keyValue.getCardIds()
            );
            return MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, keyValue.getGameId())
                .build();
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
