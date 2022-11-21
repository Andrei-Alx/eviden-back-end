package nl.fontys.atosgame.gameappbff.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.gameappbff.controller.GameSocketController;
import nl.fontys.atosgame.gameappbff.enums.RoundStatus;
import nl.fontys.atosgame.gameappbff.model.PlayerRound;
import nl.fontys.atosgame.gameappbff.model.Round;
import nl.fontys.atosgame.gameappbff.repository.RoundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        roundService =
            new RoundServiceImpl(gameService, roundRepository, gameSocketController);
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

        Round result = roundService.startRound(roundId, gameId);

        verify(roundRepository).findById(roundId);
        verify(roundRepository).save(round);
        assertEquals(RoundStatus.IN_PROGRESS, result.getStatus());
        verify(gameSocketController).roundStarted(gameId, roundId);
    }

    @Test
    void endRound() {
        Round round = new Round();
        UUID roundId = UUID.randomUUID();
        UUID gameId = UUID.randomUUID();
        when(roundRepository.findById(any())).thenReturn(Optional.of(round));

        Round result = roundService.endRound(roundId, gameId);

        verify(roundRepository).findById(roundId);
        verify(roundRepository).save(round);
        assertEquals(RoundStatus.FINISHED, result.getStatus());
        verify(gameSocketController).roundEnded(gameId, roundId);
    }

    @Test
    void addPlayerRound() {
        // Arrange
        Round round = new Round();
        UUID roundId = UUID.randomUUID();
        UUID gameId = UUID.randomUUID();
        PlayerRound playerRound = new PlayerRound();
        when(roundRepository.findById(any())).thenReturn(Optional.of(round));

        // Act
        Round result = roundService.addPlayerRound(roundId, playerRound);

        // Assert
        verify(roundRepository).save(round);
        assertEquals(1, result.getPlayerRounds().size());
        assertEquals(playerRound, result.getPlayerRounds().get(0));
    }
}
