package nl.fontys.atosgame.roundservice.service;

import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.applicationevents.PlayerRoundFinishedAppEvent;
import nl.fontys.atosgame.roundservice.model.Game;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import nl.fontys.atosgame.roundservice.model.Round;
import nl.fontys.atosgame.roundservice.service.helpers.PlayerRoundFinishedHandler;
import nl.fontys.atosgame.roundservice.service.interfaces.GameService;
import nl.fontys.atosgame.roundservice.service.interfaces.RoundService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerRoundFinishedHandlerTest {

    private RoundService roundService;
    private GameService gameService;
    private PlayerRoundFinishedHandler playerRoundFinishedHandler;

    @BeforeEach
    void setUp() {
        roundService = mock(RoundService.class);
        gameService = mock(GameService.class);
        playerRoundFinishedHandler =
            new PlayerRoundFinishedHandler(roundService, gameService);
    }

    @Test
    void onApplicationEvent() {
        UUID playerRoundId = UUID.randomUUID();
        PlayerRound playerRound = new PlayerRound();
        Round round = new Round();
        UUID roundId = UUID.randomUUID();
        round.setId(roundId);
        when(roundService.getRoundByPlayerRound(playerRound))
            .thenReturn(Optional.of(round));
        UUID gameId = UUID.randomUUID();
        Game game = new Game();
        game.setId(gameId);
        when(gameService.getGameByRoundId(roundId)).thenReturn(Optional.of(game));

        playerRoundFinishedHandler.onApplicationEvent(
            new PlayerRoundFinishedAppEvent(this, playerRound)
        );

        verify(roundService).checkRoundEnd(roundId, gameId);
    }
}
