package nl.fontys.atosgame.roundservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.applicationevents.PlayerRoundFinishedAppEvent;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import nl.fontys.atosgame.roundservice.model.Round;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerRoundFinishedHandlerTest {

    private RoundService roundService;
    private PlayerRoundFinishedHandler playerRoundFinishedHandler;

    @BeforeEach
    void setUp() {
        roundService = mock(RoundService.class);
        playerRoundFinishedHandler = new PlayerRoundFinishedHandler(roundService);
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

        playerRoundFinishedHandler.onApplicationEvent(
            new PlayerRoundFinishedAppEvent(this, playerRound)
        );

        verify(roundService).checkRoundEnd(roundId);
    }
}
