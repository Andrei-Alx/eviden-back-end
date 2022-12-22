package nl.fontys.atosgame.gameservice.model;

import nl.fontys.atosgame.gameservice.enums.RoundStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void isDone() {
        Game game = new Game();
        List<Round> rounds = List.of(
            new Round(),
            new Round(),
            new Round()
        );
        rounds.forEach(round -> round.setStatus(RoundStatus.FINISHED));
        game.setRounds(rounds);

        assertTrue(game.isDone());
    }

    @Test
    void isNotDone() {
        Game game = new Game();
        List<Round> rounds = List.of(
            new Round(),
            new Round(),
            new Round()
        );
        rounds.forEach(round -> round.setStatus(RoundStatus.FINISHED));
        rounds.get(0).setStatus(RoundStatus.CREATED);
        game.setRounds(rounds);

        assertFalse(game.isDone());
    }
}