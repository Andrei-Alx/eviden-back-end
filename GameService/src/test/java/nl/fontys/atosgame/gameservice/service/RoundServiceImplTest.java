package nl.fontys.atosgame.gameservice.service;

import nl.fontys.atosgame.gameservice.enums.RoundStatus;
import nl.fontys.atosgame.gameservice.model.Round;
import nl.fontys.atosgame.gameservice.repository.RoundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RoundServiceImplTest {
    private RoundRepository roundRepository;
    private GameService gameService;
    private ApplicationEventPublisher applicationEventPublisher;
    private RoundServiceImpl roundService;

    @BeforeEach
    void setUp() {
        roundRepository = mock(RoundRepository.class);
        gameService = mock(GameService.class);
        applicationEventPublisher = mock(ApplicationEventPublisher.class);
        roundService = new RoundServiceImpl(roundRepository, gameService, applicationEventPublisher);

        // Set behavior of mocks
        when(roundRepository.findById(any())).thenAnswer(invocation -> {
            UUID id = invocation.getArgument(0);
            Round round = new Round();
            round.setId(id);
            return Optional.of(round);
        });

        when(roundRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void startRound() {
        UUID roundId = UUID.randomUUID();
        Round round = roundService.startRound(roundId);

        assertEquals(RoundStatus.IN_PROGRESS, round.getStatus());
        assertEquals(roundId, round.getId());
        verify(roundRepository, times(1)).save(any());
    }

    @Test
    void endRound() {
        UUID roundId = UUID.randomUUID();
        Round round = roundService.endRound(roundId);

        assertEquals(RoundStatus.FINISHED, round.getStatus());
        assertEquals(roundId, round.getId());
        verify(roundRepository, times(1)).save(any());
    }

    @Test
    void createRound() {
        UUID gameId = UUID.randomUUID();
        Round round = new Round();

        Round result = roundService.createRound(gameId, round);

        verify(roundRepository, times(1)).save(round);
        verify(gameService, times(1)).addRound(gameId, round);
    }
}