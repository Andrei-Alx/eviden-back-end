package nl.fontys.atosgame.gameappbff.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import nl.fontys.atosgame.gameappbff.event.consumed.PlayerCardsDistributedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.PlayerPhaseEndedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.PlayerPhaseStartedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.RoundCreatedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.RoundEndedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.RoundStartedEvent;
import nl.fontys.atosgame.gameappbff.model.Round;
import nl.fontys.atosgame.gameappbff.model.RoundSettings;
import nl.fontys.atosgame.gameappbff.service.PlayerRoundService;
import nl.fontys.atosgame.gameappbff.service.RoundService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;

class RoundConsumersTest {

    private RoundConsumers roundConsumers;
    private RoundService roundService;
    private PlayerRoundService playerRoundService;

    @BeforeEach
    void setUp() {
        roundService = mock(RoundService.class);
        playerRoundService = mock(PlayerRoundService.class);
        roundConsumers = new RoundConsumers(roundService, playerRoundService);
    }

    @Test
    void handleRoundCreated() {
        Message<RoundCreatedEvent> message = mock(Message.class);
        RoundCreatedEvent event = mock(RoundCreatedEvent.class);
        when(message.getPayload()).thenReturn(event);
        when(event.getRound()).thenReturn(mock(Round.class));

        roundConsumers.handleRoundCreated().apply(message);

        verify(roundService).handleRoundCreatedEvent(event.getRound(), event.getGameId());
    }

    @Test
    void handleRoundStarted() {
        Message<RoundStartedEvent> message = mock(Message.class);
        RoundStartedEvent event = mock(RoundStartedEvent.class);
        when(message.getPayload()).thenReturn(event);

        roundConsumers.handleRoundStarted().apply(message);

        verify(roundService).startRound(event.getRoundId(), event.getGameId());
    }

    @Test
    void handleRoundEnded() {
        Message<RoundEndedEvent> message = mock(Message.class);
        RoundEndedEvent event = mock(RoundEndedEvent.class);
        when(message.getPayload()).thenReturn(event);

        roundConsumers.handleRoundEnded().apply(message);

        verify(roundService).endRound(event.getRoundId(), event.getGameId());
    }

    @Test
    void handlePlayerPhaseStarted() {
        Message<PlayerPhaseStartedEvent> message = mock(Message.class);
        PlayerPhaseStartedEvent event = mock(PlayerPhaseStartedEvent.class);
        when(message.getPayload()).thenReturn(event);

        roundConsumers.handlePlayerPhaseStarted().apply(message);

        verify(playerRoundService)
            .startPhase(
                event.getPlayerId(),
                event.getRoundId(),
                event.getGameId(),
                event.getPhaseNumber()
            );
    }

    @Test
    void handlePlayerPhaseEnded() {
        Message<PlayerPhaseEndedEvent> message = mock(Message.class);
        PlayerPhaseEndedEvent event = mock(PlayerPhaseEndedEvent.class);
        when(message.getPayload()).thenReturn(event);

        roundConsumers.handlePlayerPhaseEnded().apply(message);

        verify(playerRoundService)
            .endPhase(
                event.getPlayerId(),
                event.getRoundId(),
                event.getGameId(),
                event.getPhaseNumber()
            );
    }

    @Test
    void handlePlayerCardsDistributed() {
        Message<PlayerCardsDistributedEvent> message = mock(Message.class);
        PlayerCardsDistributedEvent event = mock(PlayerCardsDistributedEvent.class);
        when(message.getPayload()).thenReturn(event);

        roundConsumers.handlePlayerCardsDistributed().apply(message);

        verify(playerRoundService)
            .distributeCards(
                event.getPlayerId(),
                event.getRoundId(),
                event.getGameId(),
                event.getCardIds()
            );
    }
}
