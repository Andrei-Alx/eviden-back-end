package nl.fontys.atosgame.roundservice.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.enums.PlayerRoundPhase;
import org.junit.jupiter.api.Test;

class PlayerRoundTest {

    @Test
    void hasDeterminateResult() {
        PlayerRound playerRound = new PlayerRound();
        List<Card> selectedCards = new ArrayList<>(
            List.of(
                new Card(
                    UUID.randomUUID(),
                    List.of(new Tag("color", "red"), new Tag("shape", "circle"))
                ),
                new Card(
                    UUID.randomUUID(),
                    List.of(new Tag("color", "blue"), new Tag("shape", "square"))
                ),
                new Card(
                    UUID.randomUUID(),
                    List.of(new Tag("color", "red"), new Tag("shape", "triangle"))
                )
            )
        );
        playerRound.setSelectedCards(selectedCards);
        playerRound.setImportantTag("color");

        assertTrue(playerRound.hasDeterminateResult());
    }

    @Test
    void hasDeterminateResultIsIndeterminate() {
        PlayerRound playerRound = new PlayerRound();
        List<Card> selectedCards = new ArrayList<>(
            List.of(
                new Card(
                    UUID.randomUUID(),
                    List.of(new Tag("color", "red"), new Tag("shape", "circle"))
                ),
                new Card(
                    UUID.randomUUID(),
                    List.of(new Tag("color", "blue"), new Tag("shape", "square"))
                ),
                new Card(
                    UUID.randomUUID(),
                    List.of(new Tag("color", "red"), new Tag("shape", "triangle"))
                ),
                new Card(
                    UUID.randomUUID(),
                    List.of(new Tag("color", "blue"), new Tag("shape", "triangle"))
                )
            )
        );
        playerRound.setSelectedCards(selectedCards);
        playerRound.setImportantTag("color");

        assertFalse(playerRound.hasDeterminateResult());
    }

    @Test
    void hasDeterminateResultOneCard() {
        PlayerRound playerRound = new PlayerRound();
        List<Card> selectedCards = new ArrayList<>(
            List.of(
                new Card(
                    UUID.randomUUID(),
                    List.of(new Tag("color", "red"), new Tag("shape", "circle"))
                )
            )
        );
        playerRound.setSelectedCards(selectedCards);
        playerRound.setImportantTag("color");

        assertTrue(playerRound.hasDeterminateResult());
    }

    @Test
    void hasDeterminateResultNoCards() {
        PlayerRound playerRound = new PlayerRound();
        List<Card> selectedCards = new ArrayList<>();
        playerRound.setSelectedCards(selectedCards);
        playerRound.setImportantTag("color");

        assertTrue(playerRound.hasDeterminateResult());
    }

    @Test
    void hasDeterminateResultWithMissingTags() {
        PlayerRound playerRound = new PlayerRound();
        List<Card> selectedCards = new ArrayList<>(
            List.of(
                new Card(
                    UUID.randomUUID(),
                    List.of(new Tag("color", "red"), new Tag("shape", "circle"))
                ),
                new Card(UUID.randomUUID(), List.of(new Tag("shape", "square"))),
                new Card(
                    UUID.randomUUID(),
                    List.of(new Tag("color", "red"), new Tag("shape", "triangle"))
                )
            )
        );
        playerRound.setSelectedCards(selectedCards);
        playerRound.setImportantTag("color");

        assertTrue(playerRound.hasDeterminateResult());
    }

    @Test
    void isDone() {
        PlayerRound playerRound = spy(new PlayerRound());
        playerRound.setSelectedCards(
            new ArrayList<>(List.of(new Card(UUID.randomUUID(), new ArrayList<>())))
        );
        playerRound.setNrOfSelectedCards(1);
        playerRound.setLikedCards(
            new ArrayList<>(
                List.of(
                    new Card(UUID.randomUUID(), new ArrayList<>()),
                    new Card(UUID.randomUUID(), new ArrayList<>())
                )
            )
        );
        playerRound.setNrOfLikedCards(2);
        when(playerRound.hasDeterminateResult()).thenReturn(true);

        assertTrue(playerRound.isDone());
    }

    @Test
    void isDoneNotDoneBecauseIndeterminate() {
        PlayerRound playerRound = spy(new PlayerRound());
        playerRound.setSelectedCards(
            new ArrayList<>(List.of(new Card(UUID.randomUUID(), new ArrayList<>())))
        );
        playerRound.setNrOfSelectedCards(1);
        playerRound.setLikedCards(
            new ArrayList<>(
                List.of(
                    new Card(UUID.randomUUID(), new ArrayList<>()),
                    new Card(UUID.randomUUID(), new ArrayList<>())
                )
            )
        );
        playerRound.setNrOfLikedCards(2);
        when(playerRound.hasDeterminateResult()).thenReturn(false);

        assertFalse(playerRound.isDone());
    }

    @Test
    void isDoneNotDoneBecauseNotEnoughLikedCards() {
        PlayerRound playerRound = spy(new PlayerRound());
        playerRound.setSelectedCards(
            new ArrayList<>(List.of(new Card(UUID.randomUUID(), new ArrayList<>())))
        );
        playerRound.setNrOfSelectedCards(1);
        playerRound.setLikedCards(
            new ArrayList<>(List.of(new Card(UUID.randomUUID(), new ArrayList<>())))
        );
        playerRound.setNrOfLikedCards(2);
        when(playerRound.hasDeterminateResult()).thenReturn(true);

        assertFalse(playerRound.isDone());
    }

    @Test
    void isDoneNotDoneBecauseNotEnoughSelectedCards() {
        PlayerRound playerRound = spy(new PlayerRound());
        playerRound.setSelectedCards(
            new ArrayList<>(List.of(new Card(UUID.randomUUID(), new ArrayList<>())))
        );
        playerRound.setNrOfSelectedCards(2);
        playerRound.setLikedCards(
            new ArrayList<>(
                List.of(
                    new Card(UUID.randomUUID(), new ArrayList<>()),
                    new Card(UUID.randomUUID(), new ArrayList<>())
                )
            )
        );
        playerRound.setNrOfLikedCards(2);
        when(playerRound.hasDeterminateResult()).thenReturn(true);

        assertFalse(playerRound.isDone());
    }

    @Test
    void getPhaseLiking() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setLikedCards(
            new ArrayList<>(List.of(new Card(UUID.randomUUID(), new ArrayList<>())))
        );
        playerRound.setNrOfLikedCards(2);
        playerRound.setSelectedCards(
            new ArrayList<>(
                List.of(
                    new Card(UUID.randomUUID(), new ArrayList<>()),
                    new Card(UUID.randomUUID(), new ArrayList<>()),
                    new Card(UUID.randomUUID(), new ArrayList<>())
                )
            )
        );
        playerRound.setNrOfSelectedCards(4);

        assertEquals(PlayerRoundPhase.LIKING, playerRound.getPhase());
    }

    @Test
    void getPhasePicking() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setLikedCards(
            new ArrayList<>(List.of(new Card(UUID.randomUUID(), new ArrayList<>())))
        );
        playerRound.setNrOfLikedCards(1);
        playerRound.setSelectedCards(
            new ArrayList<>(
                List.of(
                    new Card(UUID.randomUUID(), new ArrayList<>()),
                    new Card(UUID.randomUUID(), new ArrayList<>())
                )
            )
        );
        playerRound.setNrOfSelectedCards(4);

        assertEquals(PlayerRoundPhase.PICKING, playerRound.getPhase());
    }

    @Test
    void getPhaseResult() {
        PlayerRound playerRound = spy(new PlayerRound());
        playerRound.setLikedCards(
            new ArrayList<>(
                List.of(
                    new Card(UUID.randomUUID(), new ArrayList<>()),
                    new Card(UUID.randomUUID(), new ArrayList<>())
                )
            )
        );
        playerRound.setNrOfLikedCards(2);
        playerRound.setSelectedCards(
            new ArrayList<>(
                List.of(
                    new Card(UUID.randomUUID(), new ArrayList<>()),
                    new Card(UUID.randomUUID(), new ArrayList<>()),
                    new Card(UUID.randomUUID(), new ArrayList<>())
                )
            )
        );
        playerRound.setNrOfSelectedCards(3);
        when(playerRound.hasDeterminateResult()).thenReturn(true);

        assertEquals(PlayerRoundPhase.RESULT, playerRound.getPhase());
    }
}
