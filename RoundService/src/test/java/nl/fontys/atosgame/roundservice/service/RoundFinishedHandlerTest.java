package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.applicationevents.RoundFinishedAppEvent;
import nl.fontys.atosgame.roundservice.model.Game;
import nl.fontys.atosgame.roundservice.model.Round;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoundFinishedHandlerTest {

    private GameService gameService;
    private RoundFinishedHandler roundFinishedHandler;

    @BeforeEach
    void setUp() {
        gameService = mock(GameService.class);
        roundFinishedHandler = new RoundFinishedHandler(gameService);
    }

    @Test
    void onApplicationEvent() {
        UUID roundId = UUID.randomUUID();
        Game game = mock(Game.class);
        Round round = mock(Round.class);
        when(round.getId()).thenReturn(roundId);
        when(gameService.getGameByRoundId(roundId)).thenReturn(java.util.Optional.of(game));

        roundFinishedHandler.onApplicationEvent(new RoundFinishedAppEvent(this, round));

        verify(gameService, times(1)).checkForNextRound(game.getId());
    }
}