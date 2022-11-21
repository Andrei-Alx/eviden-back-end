package nl.fontys.atosgame.gameappbff.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.gameappbff.controller.GameSocketController;
import nl.fontys.atosgame.gameappbff.model.PlayerRound;
import nl.fontys.atosgame.gameappbff.model.Round;
import nl.fontys.atosgame.gameappbff.repository.PlayerRoundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerRoundServiceImplTest {

    private PlayerRoundRepository playerRoundRepository;
    private RoundService roundService;
    private GameSocketController gameSocketController;
    private PlayerRoundServiceImpl playerRoundService;

    @BeforeEach
    void setUp() {
        playerRoundRepository = mock(PlayerRoundRepository.class);
        roundService = mock(RoundService.class);
        gameSocketController = mock(GameSocketController.class);
        playerRoundService =
            spy(
                new PlayerRoundServiceImpl(
                    playerRoundRepository,
                    roundService,
                    gameSocketController
                )
            );
    }

    @Test
    void createPlayerRound() {
        UUID playerId = UUID.randomUUID();
        UUID roundId = UUID.randomUUID();
        when(playerRoundRepository.save(any(PlayerRound.class)))
            .thenAnswer(i -> {
                PlayerRound playerRound = i.getArgument(0);
                playerRound.setId(UUID.randomUUID());
                return playerRound;
            });

        PlayerRound playerRound = playerRoundService.createPlayerRound(playerId, roundId);

        assertEquals(playerId, playerRound.getPlayerId());
        verify(playerRoundRepository).save(playerRound);
        verify(roundService).addPlayerRound(roundId, playerRound);
    }

    @Test
    void startPhaseDoesNotExist() {
        UUID playerId = UUID.randomUUID();
        UUID roundId = UUID.randomUUID();
        UUID gameID = UUID.randomUUID();
        int phase = 1;
        Round round = new Round();
        when(roundService.getRound(roundId)).thenReturn(Optional.of(round));
        doReturn(new PlayerRound())
            .when(playerRoundService)
            .createPlayerRound(playerId, roundId);

        playerRoundService.startPhase(playerId, roundId, gameID, phase);

        assertEquals(1, round.getPlayerRounds().size());
        assertEquals(new PlayerRound(), round.getPlayerRounds().get(0));
        verify(playerRoundRepository).save(any(PlayerRound.class));
        verify(gameSocketController).playerPhase(gameID, playerId, phase);
    }

    @Test
    void startPhaseExists() {
        UUID playerId = UUID.randomUUID();
        UUID roundId = UUID.randomUUID();
        UUID gameID = UUID.randomUUID();
        int phase = 1;
        Round round = new Round();
        PlayerRound playerRound = new PlayerRound();
        playerRound.setPlayerId(playerId);
        round.getPlayerRounds().add(playerRound);
        when(roundService.getRound(roundId)).thenReturn(Optional.of(round));

        playerRoundService.startPhase(playerId, roundId, gameID, phase);

        assertEquals(1, round.getPlayerRounds().size());
        assertEquals(playerRound, round.getPlayerRounds().get(0));
        verify(playerRoundRepository).save(any(PlayerRound.class));
        verify(gameSocketController).playerPhase(gameID, playerId, phase);
    }

    @Test
    void endPhase() {
        UUID playerId = UUID.randomUUID();
        UUID roundId = UUID.randomUUID();
        UUID gameID = UUID.randomUUID();
        int phase = 1;
        Round round = new Round();
        PlayerRound playerRound = new PlayerRound();
        playerRound.setPlayerId(playerId);
        round.getPlayerRounds().add(playerRound);
        when(roundService.getRound(roundId)).thenReturn(Optional.of(round));

        playerRoundService.endPhase(playerId, roundId, gameID, phase);

        verify(playerRoundRepository).save(any(PlayerRound.class));
    }
}
