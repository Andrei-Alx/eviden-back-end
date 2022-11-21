package nl.fontys.atosgame.gameappbff.controller;

import nl.fontys.atosgame.gameappbff.event.consumed.RoundCreatedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.RoundStartedEvent;
import nl.fontys.atosgame.gameappbff.model.Round;
import nl.fontys.atosgame.gameappbff.model.RoundSettings;
import nl.fontys.atosgame.gameappbff.service.RoundService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoundConsumersTest {

    private RoundConsumers roundConsumers;
    private RoundService roundService;

    @BeforeEach
    void setUp() {
        roundService = mock(RoundService.class);
        roundConsumers = new RoundConsumers(roundService);
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
}