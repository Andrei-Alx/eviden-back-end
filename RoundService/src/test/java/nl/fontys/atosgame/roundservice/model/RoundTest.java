package nl.fontys.atosgame.roundservice.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    @Test
    void addPlayerRound() {
        Round round = new Round();
        PlayerRound playerRound = new PlayerRound();

        round.addPlayerRound(playerRound);

        assertEquals(1, round.getPlayerRounds().size());
        assertEquals(playerRound, round.getPlayerRounds().get(0));
    }
}