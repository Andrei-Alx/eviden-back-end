package nl.fontys.atosgame.roundservice.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import nl.fontys.atosgame.roundservice.enums.RoundStatus;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    void getCurrentRound() {
        Game game = new Game();
        Round round1 = new Round();
        round1.setStatus(RoundStatus.FINISHED);
        Round round2 = new Round();
        round2.setStatus(RoundStatus.IN_PROGRESS);
        game.setRounds(List.of(round1, round2));

        assertEquals(round2, game.getCurrentRound().get());
    }

    @Test
    void getCurrentRoundNoRoundInProgress() {
        Game game = new Game();
        Round round1 = new Round();
        round1.setStatus(RoundStatus.FINISHED);
        Round round2 = new Round();
        round2.setStatus(RoundStatus.FINISHED);
        game.setRounds(List.of(round1, round2));

        assertTrue(game.getCurrentRound().isEmpty());
    }
}
