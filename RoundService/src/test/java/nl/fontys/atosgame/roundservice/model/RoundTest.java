package nl.fontys.atosgame.roundservice.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class RoundTest {

    @Test
    void addPlayerRound() {
        Round round = new Round();
        PlayerRound playerRound = new PlayerRound();

        round.addPlayerRound(playerRound);

        assertEquals(1, round.getPlayerRounds().size());
        assertEquals(playerRound, round.getPlayerRounds().get(0));
    }

    @Test
    void isDone() {
        PlayerRound playerRound1 = mock(PlayerRound.class);
        PlayerRound playerRound2 = mock(PlayerRound.class);
        PlayerRound playerRound3 = mock(PlayerRound.class);
        when(playerRound1.isDone()).thenReturn(true);
        when(playerRound2.isDone()).thenReturn(true);
        when(playerRound3.isDone()).thenReturn(true);
        Round round = new Round();
        round.setPlayerRounds(List.of(playerRound1, playerRound2, playerRound3));

        assertTrue(round.isDone());
    }

    @Test
    void isNotDone() {
        PlayerRound playerRound1 = mock(PlayerRound.class);
        PlayerRound playerRound2 = mock(PlayerRound.class);
        PlayerRound playerRound3 = mock(PlayerRound.class);
        when(playerRound1.isDone()).thenReturn(true);
        when(playerRound2.isDone()).thenReturn(true);
        when(playerRound3.isDone()).thenReturn(false);
        Round round = new Round();
        round.setPlayerRounds(List.of(playerRound1, playerRound2, playerRound3));

        assertFalse(round.isDone());
    }

    @Test
    void getPlayerRound() {
        UUID playerId = UUID.randomUUID();
        PlayerRound playerRound1 = new PlayerRound();
        playerRound1.setPlayerId(UUID.randomUUID());
        PlayerRound playerRound2 = new PlayerRound();
        playerRound2.setPlayerId(playerId);
        PlayerRound playerRound3 = new PlayerRound();
        playerRound3.setPlayerId(UUID.randomUUID());
        Round round = new Round();
        round.setPlayerRounds(List.of(playerRound1, playerRound2, playerRound3));

        PlayerRound result = round.getPlayerRound(playerId);

        assertEquals(playerRound2, result);
    }
}
