package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.model.Round;
import nl.fontys.atosgame.gameappbff.repository.RoundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RoundServiceImplTest {
    private GameService gameService;
    private RoundRepository roundRepository;
    private RoundServiceImpl roundService;

    @BeforeEach
    void setUp() {
        gameService = mock(GameService.class);
        roundRepository = mock(RoundRepository.class);
        roundService = new RoundServiceImpl(gameService, roundRepository);
    }

    @Test
    void handleRoundCreatedEvent() {
        Round round = new Round();
        UUID gameId = UUID.randomUUID();

        roundService.handleRoundCreatedEvent(round, gameId);

        verify(roundRepository).save(round);
        verify(gameService).addRoundToGame(round, gameId);
    }
}