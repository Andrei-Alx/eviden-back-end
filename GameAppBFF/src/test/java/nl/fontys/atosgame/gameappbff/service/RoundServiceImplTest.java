package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.controller.GameSocketController;
import nl.fontys.atosgame.gameappbff.model.Round;
import nl.fontys.atosgame.gameappbff.repository.RoundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoundServiceImplTest {
    private GameService gameService;
    private RoundRepository roundRepository;
    private GameSocketController gameSocketController;
    private RoundServiceImpl roundService;

    @BeforeEach
    void setUp() {
        gameService = mock(GameService.class);
        roundRepository = mock(RoundRepository.class);
        gameSocketController = mock(GameSocketController.class);
        roundService = new RoundServiceImpl(gameService, roundRepository, gameSocketController);
    }

    @Test
    void handleRoundCreatedEvent() {
        Round round = new Round();
        UUID gameId = UUID.randomUUID();

        roundService.handleRoundCreatedEvent(round, gameId);

        verify(roundRepository).save(round);
        verify(gameService).addRoundToGame(round, gameId);
    }

    @Test
    void startRound() {
        Round round = new Round();
        UUID roundId = UUID.randomUUID();
        UUID gameId = UUID.randomUUID();
        when(roundRepository.findById(roundId)).thenReturn(Optional.of(round));

        roundService.startRound(roundId, gameId);

        verify(roundRepository).findById(roundId);
        verify(roundRepository).save(round);
        verify(gameSocketController).roundStarted(gameId, roundId);
    }
}