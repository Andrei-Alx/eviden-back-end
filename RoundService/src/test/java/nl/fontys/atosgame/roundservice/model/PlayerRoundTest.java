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

    @Test
    void addLikedCardCorrectFlow() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setLikedCards(new ArrayList<>());
        Card card = new Card(UUID.randomUUID(), new ArrayList<>());
        playerRound.setDistributedCards(
            new ArrayList<>(List.of(card, new Card(UUID.randomUUID(), new ArrayList<>())))
        );

        playerRound.addLikedCard(card);

        assertEquals(1, playerRound.getLikedCards().size());
        assertEquals(card, playerRound.getLikedCards().get(0));
    }

    @Test
    void addLikedCardNotInHand() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setLikedCards(new ArrayList<>());
        Card card = new Card(UUID.randomUUID(), new ArrayList<>());

        assertThrows(IllegalArgumentException.class, () -> playerRound.addLikedCard(card));
    }

    @Test
    void addLikedCardAlreadyLiked() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setLikedCards(new ArrayList<>());
        Card card = new Card(UUID.randomUUID(), new ArrayList<>());

        assertThrows(IllegalArgumentException.class, () -> playerRound.addLikedCard(card));
    }

    @Test
    void addDislikedCardCorrectFlow() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setDislikedCards(new ArrayList<>());
        Card card = new Card(UUID.randomUUID(), new ArrayList<>());
        playerRound.setDistributedCards(
            new ArrayList<>(List.of(card, new Card(UUID.randomUUID(), new ArrayList<>())))
        );

        playerRound.addDislikedCard(card);

        assertEquals(1, playerRound.getDislikedCards().size());
        assertEquals(card, playerRound.getDislikedCards().get(0));
    }

    @Test
    void addDislikedCardNotInHand() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setDislikedCards(new ArrayList<>());
        Card card = new Card(UUID.randomUUID(), new ArrayList<>());

        assertThrows(IllegalArgumentException.class, () -> playerRound.addDislikedCard(card));
    }

    @Test
    void addDislikedCardAlreadyDisliked() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setDislikedCards(new ArrayList<>());
        Card card = new Card(UUID.randomUUID(), new ArrayList<>());

        assertThrows(IllegalArgumentException.class, () -> playerRound.addDislikedCard(card));
    }

    @Test
    void addSelectedCardsCorrectFlow() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setSelectedCards(new ArrayList<>());
        Card card1 = new Card(UUID.randomUUID(), new ArrayList<>());
        Card card2 = new Card(UUID.randomUUID(), new ArrayList<>());
        List<Card> cards = new ArrayList<>(List.of(
                card1,
                card2
                ));
        playerRound.setDistributedCards(
            new ArrayList<>(List.of(card1, card2))
        );

        playerRound.addSelectedCards(cards);

        assertEquals(2, playerRound.getSelectedCards().size());
        assertEquals(card1, playerRound.getSelectedCards().get(0));
        assertEquals(card2, playerRound.getSelectedCards().get(1));
    }

    @Test
    void addSelectedCardsNotInHand() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setSelectedCards(new ArrayList<>());
        Card card1 = new Card(UUID.randomUUID(), new ArrayList<>());
        Card card2 = new Card(UUID.randomUUID(), new ArrayList<>());
        List<Card> cards = new ArrayList<>(List.of(
                card1,
                card2
                ));

        assertThrows(IllegalArgumentException.class, () -> playerRound.addSelectedCards(cards));
    }

    @Test
    void addSelectedCardsAlreadySelected() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setSelectedCards(new ArrayList<>());
        Card card1 = new Card(UUID.randomUUID(), new ArrayList<>());
        Card card2 = new Card(UUID.randomUUID(), new ArrayList<>());
        List<Card> cards = new ArrayList<>(List.of(
                card1,
                card2
                ));
        playerRound.setSelectedCards(cards);

        assertThrows(IllegalArgumentException.class, () -> playerRound.addSelectedCards(cards));
    }


}
