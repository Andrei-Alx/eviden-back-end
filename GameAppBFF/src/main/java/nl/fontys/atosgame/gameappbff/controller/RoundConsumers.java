package nl.fontys.atosgame.gameappbff.controller;

import java.util.function.Function;
import nl.fontys.atosgame.gameappbff.event.consumed.*;
import nl.fontys.atosgame.gameappbff.service.PlayerRoundService;
import nl.fontys.atosgame.gameappbff.service.RoundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

/**
 * Collection of consumers for round related events:
 * - RoundStartedEvent
 * - RoundEndedEvent
 * - PlayerPhaseStartedEvent
 * - PlayerPhaseEndedEvent
 * - PlayerCardsDistributedEvent
 * - PlayerLikedCardEvent
 * - PlayerDislikedCardEvent
 * - PlayerSelectedCardsEvent
 * - PlayerResultDeterminedEvent
 * - PlayerResultIndeterminateEvent
 */
@Controller
public class RoundConsumers {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoundConsumers.class);
    private RoundService roundService;
    private PlayerRoundService playerRoundService;

    public RoundConsumers(
        @Autowired RoundService roundService,
        @Autowired PlayerRoundService playerRoundService
    ) {
        this.roundService = roundService;
        this.playerRoundService = playerRoundService;
    }

    /**
     * Id: C-55
     * Consumer for RoundCreatedEvent
     * input topic: round-created-topic
     * output topic: -
     */
    @Bean
    public Function<Message<RoundCreatedEvent>, Void> handleRoundCreated() {
        return message -> {
            RoundCreatedEvent event = message.getPayload();
            roundService.handleRoundCreatedEvent(event.getRound(), event.getGameId());
            return null;
        };
    }

    /**
     * Id: C-30
     * Consumer for RoundStartedEvent
     * input topic: round-started-topic
     * output topic: -
     */
    @Bean
    public Function<Message<RoundStartedEvent>, Void> handleRoundStarted() {
        return message -> {
            RoundStartedEvent event = message.getPayload();
            roundService.startRound(event.getRoundId(), event.getGameId());
            return null;
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
            RoundEndedEvent event = message.getPayload();
            roundService.endRound(event.getRoundId(), event.getGameId());
            return null;
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
            PlayerPhaseStartedEvent event = message.getPayload();
            playerRoundService.startPhase(
                event.getPlayerId(),
                event.getRoundId(),
                event.getGameId(),
                event.getPhaseNumber()
            );
            return null;
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
            PlayerPhaseEndedEvent event = message.getPayload();
            playerRoundService.endPhase(
                event.getPlayerId(),
                event.getRoundId(),
                event.getGameId(),
                event.getPhaseNumber()
            );
            return null;
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
            PlayerCardsDistributedEvent event = message.getPayload();
            playerRoundService.distributeCards(
                event.getPlayerId(),
                event.getRoundId(),
                event.getGameId(),
                event.getCardIds()
            );
            return null;
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
            LOGGER.info(String.format("handle liking card => %s", message));
            PlayerLikedCardEvent event = message.getPayload();
            playerRoundService.likeCard(
                event.getPlayerId(),
                event.getRoundId(),
                event.getGameId(),
                event.getCardId()
            );
            return null;
        };
    }

    /**
     * Id: C-36
     * Consumer for PlayerDislikedCardEvent
     * input topic: player-disliked-card-topic
     * output topic: -
     */
    @Bean
    public Function<Message<PlayerDislikedCardEvent>, Void> handlePlayerDislikedCard() {
        return message -> {
            PlayerDislikedCardEvent event = message.getPayload();
            playerRoundService.dislikeCard(
                event.getPlayerId(),
                event.getRoundId(),
                event.getGameId(),
                event.getCardId()
            );
            return null;
        };
    }

    /**
     * Id: C-37
     * Consumer for PlayerSelectedCardsEvent
     * input topic: player-selected-cards-topic
     * output topic: -
     */
    @Bean
    public Function<Message<PlayerSelectedCardsEvent>, Void> handlePlayerSelectedCards() {
        return message -> {
            PlayerSelectedCardsEvent event = message.getPayload();
            playerRoundService.selectCards(
                event.getPlayerId(),
                event.getRoundId(),
                event.getGameId(),
                event.getCardIds()
            );
            return null;
        };
    }
    /*@Bean
    public Function<Message<PlayerResultDeterminedEvent>, Void> handlePlayerResultDetermined(){
        return message -> {
            PlayerResultDeterminedEvent event = message.getPayload();
            roundService.res(
                    event.getPlayerId(),
                    event.getRoundId(),
                    event.getGameId(),
                    event.getResult()
            );
            return null;
        };
    }*/
}
