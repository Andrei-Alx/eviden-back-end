package nl.fontys.atosgame.roundservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import nl.fontys.atosgame.roundservice.dto.*;
import nl.fontys.atosgame.roundservice.enums.ResultStatus;
import nl.fontys.atosgame.roundservice.event.produced.*;
import nl.fontys.atosgame.roundservice.model.Round;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;

class RoundEventProducersTest {

    @Test
    void produceRoundCreated() {
        RoundEventProducers roundEventProducers = new RoundEventProducers();
        Function<RoundCreatedEventKeyValue, Message<RoundCreatedEvent>> function = roundEventProducers.produceRoundCreated();
        RoundCreatedEventKeyValue roundCreatedEventKeyValue = new RoundCreatedEventKeyValue(
            mock(UUID.class),
            mock(Round.class)
        );

        Message<RoundCreatedEvent> message = function.apply(roundCreatedEventKeyValue);

        assertInstanceOf(RoundCreatedEvent.class, message.getPayload());
        assertEquals("RoundService", message.getPayload().getService());
        assertEquals("RoundCreated", message.getPayload().getType());
        assertEquals(
            roundCreatedEventKeyValue.getRound(),
            message.getPayload().getRound()
        );
        assertEquals(
            roundCreatedEventKeyValue.getGameId(),
            message.getHeaders().get(KafkaHeaders.MESSAGE_KEY)
        );
    }

    @Test
    void producePlayerCardsDistributed() {
        CardsDistributedDto cardsDistributedDto = new CardsDistributedDto(
            UUID.randomUUID(),
            mock(List.class),
            UUID.randomUUID(),
            UUID.randomUUID()
        );
        RoundEventProducers roundEventProducers = new RoundEventProducers();

        Message<PlayerCardsDistributed> message = roundEventProducers
            .producePlayerCardsDistributed()
            .apply(cardsDistributedDto);

        assertEquals("RoundService", message.getPayload().getService());
        assertEquals("PlayerCardsDistributed", message.getPayload().getType());
        assertEquals(cardsDistributedDto.getRoundId(), message.getPayload().getRoundId());
        assertEquals(
            cardsDistributedDto.getPlayerId(),
            message.getPayload().getPlayerId()
        );
        assertEquals(cardsDistributedDto.getCardIds(), message.getPayload().getCardIds());
        assertEquals(
            cardsDistributedDto.getGameId(),
            message.getHeaders().get(KafkaHeaders.MESSAGE_KEY)
        );
    }

    @Test
    void produceRoundStarted() {
        RoundStartedDto roundStartedDto = new RoundStartedDto(
            UUID.randomUUID(),
            UUID.randomUUID()
        );
        RoundEventProducers roundEventProducers = new RoundEventProducers();

        Message<RoundStartedEvent> message = roundEventProducers
            .produceRoundStarted()
            .apply(roundStartedDto);

        assertEquals("RoundService", message.getPayload().getService());
        assertEquals("RoundStarted", message.getPayload().getType());
        assertEquals(roundStartedDto.getRoundId(), message.getPayload().getRoundId());
        assertEquals(roundStartedDto.getGameId(), message.getPayload().getGameId());
        assertEquals(
            roundStartedDto.getGameId(),
            message.getHeaders().get(KafkaHeaders.MESSAGE_KEY)
        );
    }

    @Test
    void producePlayerPhaseStarted() {
        PlayerPhaseStartedDto playerPhaseStartedDto = new PlayerPhaseStartedDto(
            0,
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
        );
        RoundEventProducers roundEventProducers = new RoundEventProducers();

        Message<PlayerPhaseStartedEvent> message = roundEventProducers
            .producePlayerPhaseStarted()
            .apply(playerPhaseStartedDto);

        assertEquals("RoundService", message.getPayload().getService());
        assertEquals("PlayerPhaseStarted", message.getPayload().getType());
        assertEquals(
            playerPhaseStartedDto.getRoundId(),
            message.getPayload().getRoundId()
        );
        assertEquals(
            playerPhaseStartedDto.getPlayerId(),
            message.getPayload().getPlayerId()
        );
        assertEquals(playerPhaseStartedDto.getGameId(), message.getPayload().getGameId());
        assertEquals(
            playerPhaseStartedDto.getPhaseNumber(),
            message.getPayload().getPhaseNumber()
        );
        assertEquals(
            playerPhaseStartedDto.getGameId(),
            message.getHeaders().get(KafkaHeaders.MESSAGE_KEY)
        );
    }

    @Test
    void produceRoundEnded() {
        RoundEndedDto roundEndedDto = new RoundEndedDto(
            UUID.randomUUID(),
            UUID.randomUUID()
        );
        RoundEventProducers roundEventProducers = new RoundEventProducers();

        Message<RoundEndedEvent> message = roundEventProducers
            .produceRoundEnded()
            .apply(roundEndedDto);

        assertEquals("RoundService", message.getPayload().getService());
        assertEquals("RoundEnded", message.getPayload().getType());
        assertEquals(roundEndedDto.getRoundId(), message.getPayload().getRoundId());
        assertEquals(roundEndedDto.getGameId(), message.getPayload().getGameId());
        assertEquals(
            roundEndedDto.getGameId(),
            message.getHeaders().get(KafkaHeaders.MESSAGE_KEY)
        );
    }

    @Test
    void producePlayerPhaseEnded() {
        PlayerPhaseEndedDto playerPhaseEndedDto = new PlayerPhaseEndedDto(
            0,
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
        );
        RoundEventProducers roundEventProducers = new RoundEventProducers();

        Message<PlayerPhaseEndedEvent> message = roundEventProducers
            .producePlayerPhaseEnded()
            .apply(playerPhaseEndedDto);

        assertEquals("RoundService", message.getPayload().getService());
        assertEquals("PlayerPhaseEnded", message.getPayload().getType());
        assertEquals(playerPhaseEndedDto.getRoundId(), message.getPayload().getRoundId());
        assertEquals(
            playerPhaseEndedDto.getPlayerId(),
            message.getPayload().getPlayerId()
        );
        assertEquals(playerPhaseEndedDto.getGameId(), message.getPayload().getGameId());
        assertEquals(
            playerPhaseEndedDto.getPhaseNumber(),
            message.getPayload().getPhaseNumber()
        );
        assertEquals(
            playerPhaseEndedDto.getGameId(),
            message.getHeaders().get(KafkaHeaders.MESSAGE_KEY)
        );
    }

    @Test
    void producePlayerLikedCard() {
        CardLikedEventDto playerLikedCardDto = new CardLikedEventDto(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
        );
        RoundEventProducers roundEventProducers = new RoundEventProducers();

        Message<PlayerLikedCard> message = roundEventProducers
            .producePlayerLikedCard()
            .apply(playerLikedCardDto);

        assertEquals("RoundService", message.getPayload().getService());
        assertEquals("PlayerLikedCard", message.getPayload().getType());
        assertEquals(playerLikedCardDto.getRoundId(), message.getPayload().getRoundId());
        assertEquals(
            playerLikedCardDto.getPlayerId(),
            message.getPayload().getPlayerId()
        );
        assertEquals(playerLikedCardDto.getGameId(), message.getPayload().getGameId());
        assertEquals(
            playerLikedCardDto.getCardId(),
            message.getPayload().getCardId()
        );
        assertEquals(
            playerLikedCardDto.getGameId(),
            message.getHeaders().get(KafkaHeaders.MESSAGE_KEY)
        );
    }

    @Test
    void producePlayerDislikedCard() {
        CardDislikedEventDto playerDislikedCardDto = new CardDislikedEventDto(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
        );
        RoundEventProducers roundEventProducers = new RoundEventProducers();

        Message<PlayerDislikedCard> message = roundEventProducers
            .producePlayerDislikedCard()
            .apply(playerDislikedCardDto);

        assertEquals("RoundService", message.getPayload().getService());
        assertEquals("PlayerDislikedCard", message.getPayload().getType());
        assertEquals(
            playerDislikedCardDto.getRoundId(),
            message.getPayload().getRoundId()
        );
        assertEquals(
            playerDislikedCardDto.getPlayerId(),
            message.getPayload().getPlayerId()
        );
        assertEquals(playerDislikedCardDto.getGameId(), message.getPayload().getGameId());
        assertEquals(
            playerDislikedCardDto.getCardId(),
            message.getPayload().getCardId()
        );
        assertEquals(
            playerDislikedCardDto.getGameId(),
            message.getHeaders().get(KafkaHeaders.MESSAGE_KEY)
        );
    }

    @Test
    void producePlayerSelectedCards() {
        CardsSelectedEventDto playerSelectedCardsDto = new CardsSelectedEventDto(
            UUID.randomUUID(),
                List.of(UUID.randomUUID(), UUID.randomUUID()),
            UUID.randomUUID(),
            UUID.randomUUID()
        );
        RoundEventProducers roundEventProducers = new RoundEventProducers();

        Message<PlayerSelectedCards> message = roundEventProducers
            .producePlayerSelectedCards()
            .apply(playerSelectedCardsDto);

        assertEquals("RoundService", message.getPayload().getService());
        assertEquals("PlayerSelectedCards", message.getPayload().getType());
        assertEquals(
            playerSelectedCardsDto.getRoundId(),
            message.getPayload().getRoundId()
        );
        assertEquals(
            playerSelectedCardsDto.getPlayerId(),
            message.getPayload().getPlayerId()
        );
        assertEquals(playerSelectedCardsDto.getGameId(), message.getPayload().getGameId());
        assertEquals(
            playerSelectedCardsDto.getCardIds(),
            message.getPayload().getCardIds()
        );
        assertEquals(
            playerSelectedCardsDto.getGameId(),
            message.getHeaders().get(KafkaHeaders.MESSAGE_KEY)
        );
    }

    @Test
    void producePlayerResultDetermined(){
        ResultDto result = new ResultDto();
        result.setStatus(ResultStatus.DETERMINED);
        PlayerResultDeterminedDto playerResultDeterminedDto = new PlayerResultDeterminedDto(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            result
        );
        RoundEventProducers roundEventProducers = new RoundEventProducers();

        Message<PlayerResultDeterminedEvent> message = roundEventProducers
            .producePlayerResultDetermined()
            .apply(playerResultDeterminedDto);

        assertEquals("RoundService", message.getPayload().getService());
        assertEquals("PlayerResultDetermined", message.getPayload().getType());
        assertEquals(playerResultDeterminedDto.getRoundId(), message.getPayload().getRoundId());
        assertEquals(playerResultDeterminedDto.getPlayerId(), message.getPayload().getPlayerId());
        assertEquals(playerResultDeterminedDto.getGameId(), message.getPayload().getGameId());
        assertEquals(playerResultDeterminedDto.getResult().getStatus(), message.getPayload().getResult().getStatus());
        assertEquals(playerResultDeterminedDto.getGameId(), message.getHeaders().get(KafkaHeaders.MESSAGE_KEY));
    }

    @Test
    void producePlayerResultIndeterminate(){
        ResultDto result = new ResultDto();
        result.setStatus(ResultStatus.INDETERMINATE);
        PlayerResultIndeterminateEvent playerResultIndeterminateEvent = new PlayerResultIndeterminateEvent(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            result.getStatus()
        );
        RoundEventProducers roundEventProducers = new RoundEventProducers();

        Message<PlayerResultIndeterminateEvent> message = roundEventProducers
            .producePlayerResultIndeterminate()
            .apply(playerResultIndeterminateEvent);

        assertEquals("RoundService", message.getPayload().getService());
        assertEquals("PlayerResultIndetermined", message.getPayload().getType());
        assertEquals(playerResultIndeterminateEvent.getRoundId(), message.getPayload().getRoundId());
        assertEquals(playerResultIndeterminateEvent.getPlayerId(), message.getPayload().getPlayerId());
        assertEquals(playerResultIndeterminateEvent.getGameId(), message.getPayload().getGameId());
        assertEquals(playerResultIndeterminateEvent.getResultStatus(), message.getPayload().getResultStatus());
        assertEquals(playerResultIndeterminateEvent.getGameId(), message.getHeaders().get(KafkaHeaders.MESSAGE_KEY));
    }
}
