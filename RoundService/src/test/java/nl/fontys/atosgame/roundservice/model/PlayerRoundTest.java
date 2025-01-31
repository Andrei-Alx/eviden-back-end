package nl.fontys.atosgame.roundservice.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.enums.PlayerRoundPhase;
import nl.fontys.atosgame.roundservice.enums.TagType;
import org.junit.jupiter.api.Test;

class PlayerRoundTest {

    //    @Test
    //    void hasDeterminateResult() {
    //        PlayerRound playerRound = new PlayerRound();
    //        List<Card> selectedCards = new ArrayList<>(
    //            List.of(
    //                new Card(
    //                    UUID.randomUUID(),
    //                    List.of(new Tag("color", "red"), new Tag("shape", "circle"))
    //                ),
    //                new Card(
    //                    UUID.randomUUID(),
    //                    List.of(new Tag("color", "blue"), new Tag("shape", "square"))
    //                ),
    //                new Card(
    //                    UUID.randomUUID(),
    //                    List.of(new Tag("color", "red"), new Tag("shape", "triangle"))
    //                )
    //            )
    //        );
    //        playerRound.setSelectedCards(selectedCards);
    //        playerRound.getRoundSettings().getCardSet().setImportantTag("color");
    //
    //        assertTrue(playerRound.hasDeterminateResult());
    //    }
    //
    //    @Test
    //    void hasDeterminateResultIsIndeterminate() {
    //        PlayerRound playerRound = new PlayerRound();
    //        List<Card> selectedCards = new ArrayList<>(
    //            List.of(
    //                new Card(
    //                    UUID.randomUUID(),
    //                    List.of(new Tag("color", "red"), new Tag("shape", "circle"))
    //                ),
    //                new Card(
    //                    UUID.randomUUID(),
    //                    List.of(new Tag("color", "blue"), new Tag("shape", "square"))
    //                ),
    //                new Card(
    //                    UUID.randomUUID(),
    //                    List.of(new Tag("color", "red"), new Tag("shape", "triangle"))
    //                ),
    //                new Card(
    //                    UUID.randomUUID(),
    //                    List.of(new Tag("color", "blue"), new Tag("shape", "triangle"))
    //                )
    //            )
    //        );
    //        playerRound.setSelectedCards(selectedCards);
    //        playerRound.getRoundSettings().getCardSet().setImportantTag("color");
    //
    //        assertFalse(playerRound.hasDeterminateResult());
    //    }
    //
    //    @Test
    //    void hasDeterminateResultOneCard() {
    //        PlayerRound playerRound = new PlayerRound();
    //        List<Card> selectedCards = new ArrayList<>(
    //            List.of(
    //                new Card(
    //                    UUID.randomUUID(),
    //                    List.of(new Tag("color", "red"), new Tag("shape", "circle"))
    //                )
    //            )
    //        );
    //        playerRound.setSelectedCards(selectedCards);
    //        playerRound.getRoundSettings().getCardSet().setImportantTag("color");
    //
    //        assertTrue(playerRound.hasDeterminateResult());
    //    }
    //
    //    @Test
    //    void hasDeterminateResultNoCards() {
    //        PlayerRound playerRound = new PlayerRound();
    //        List<Card> selectedCards = new ArrayList<>();
    //        playerRound.setSelectedCards(selectedCards);
    //        playerRound.getRoundSettings().getCardSet().setImportantTag("color");
    //
    //        assertTrue(playerRound.hasDeterminateResult());
    //    }
    //
    //    @Test
    //    void hasDeterminateResultWithMissingTags() {
    //        PlayerRound playerRound = new PlayerRound();
    //        List<Card> selectedCards = new ArrayList<>(
    //            List.of(
    //                new Card(
    //                    UUID.randomUUID(),
    //                    List.of(new Tag("color", "red"), new Tag("shape", "circle"))
    //                ),
    //                new Card(UUID.randomUUID(), List.of(new Tag("shape", "square"))),
    //                new Card(
    //                    UUID.randomUUID(),
    //                    List.of(new Tag("color", "red"), new Tag("shape", "triangle"))
    //                )
    //            )
    //        );
    //        playerRound.setSelectedCards(selectedCards);
    //        playerRound.getRoundSettings().getCardSet().setImportantTag("color");
    //
    //        assertTrue(playerRound.hasDeterminateResult());
    //    }
    //
    //    @Test
    //    void isDone() {
    //        PlayerRound playerRound = spy(new PlayerRound());
    //        playerRound.setSelectedCards(
    //            new ArrayList<>(List.of(new Card(UUID.randomUUID(), new ArrayList<>())))
    //        );
    //        playerRound.getRoundSettings().setNrOfSelectedCards(1);
    //        playerRound.setLikedCards(
    //            new ArrayList<>(
    //                List.of(
    //                    new Card(UUID.randomUUID(), new ArrayList<>()),
    //                    new Card(UUID.randomUUID(), new ArrayList<>())
    //                )
    //            )
    //        );
    //        playerRound.getRoundSettings().setNrOfLikedCards(2);
    //        when(playerRound.hasDeterminateResult()).thenReturn(true);
    //
    //        assertTrue(playerRound.isDone());
    //    }
    //
    //    @Test
    //    void isDoneNotDoneBecauseIndeterminate() {
    //        PlayerRound playerRound = spy(new PlayerRound());
    //        playerRound.setSelectedCards(
    //            new ArrayList<>(List.of(new Card(UUID.randomUUID(), new ArrayList<>())))
    //        );
    //        playerRound.getRoundSettings().setNrOfSelectedCards(1);
    //        playerRound.setLikedCards(
    //            new ArrayList<>(
    //                List.of(
    //                    new Card(UUID.randomUUID(), new ArrayList<>()),
    //                    new Card(UUID.randomUUID(), new ArrayList<>())
    //                )
    //            )
    //        );
    //        playerRound.getRoundSettings().setNrOfLikedCards(2);
    //        when(playerRound.hasDeterminateResult()).thenReturn(false);
    //
    //        assertFalse(playerRound.isDone());
    //    }
    //
    //    @Test
    //    void isDoneNotDoneBecauseNotEnoughLikedCards() {
    //        PlayerRound playerRound = spy(new PlayerRound());
    //        playerRound.setSelectedCards(
    //            new ArrayList<>(List.of(new Card(UUID.randomUUID(), new ArrayList<>())))
    //        );
    //        playerRound.getRoundSettings().setNrOfSelectedCards(1);
    //        playerRound.setLikedCards(
    //            new ArrayList<>(List.of(new Card(UUID.randomUUID(), new ArrayList<>())))
    //        );
    //        playerRound.getRoundSettings().setNrOfLikedCards(2);
    //        when(playerRound.hasDeterminateResult()).thenReturn(true);
    //
    //        assertFalse(playerRound.isDone());
    //    }
    //
    //    @Test
    //    void isDoneNotDoneBecauseNotEnoughSelectedCards() {
    //        PlayerRound playerRound = spy(new PlayerRound());
    //        playerRound.setSelectedCards(
    //            new ArrayList<>(List.of(new Card(UUID.randomUUID(), new ArrayList<>())))
    //        );
    //        playerRound.getRoundSettings().setNrOfSelectedCards(2);
    //        playerRound.setLikedCards(
    //            new ArrayList<>(
    //                List.of(
    //                    new Card(UUID.randomUUID(), new ArrayList<>()),
    //                    new Card(UUID.randomUUID(), new ArrayList<>())
    //                )
    //            )
    //        );
    //        playerRound.getRoundSettings().setNrOfLikedCards(2);
    //        when(playerRound.hasDeterminateResult()).thenReturn(true);
    //
    //        assertFalse(playerRound.isDone());
    //    }

    @Test
    void getPhaseLiking() {
        PlayerRound playerRound = new PlayerRound();
        RoundSettings roundSettings = new RoundSettings();
        roundSettings.setNrOfLikedCards(2);
        roundSettings.setNrOfSelectedCards(4);
        playerRound.setRoundSettings(roundSettings);

        playerRound.setLikedCards(
            new ArrayList<>(List.of(new Card(UUID.randomUUID(), new ArrayList<>(), true)))
        );
        playerRound.setSelectedCards(
            new ArrayList<>(
                List.of(
                    new Card(UUID.randomUUID(), new ArrayList<>(), true),
                    new Card(UUID.randomUUID(), new ArrayList<>(), true),
                    new Card(UUID.randomUUID(), new ArrayList<>(), true)
                )
            )
        );

        assertEquals(PlayerRoundPhase.LIKING, playerRound.getPhase());
    }

    @Test
    void getPhasePicking() {
        PlayerRound playerRound = new PlayerRound();
        RoundSettings roundSettings = new RoundSettings();
        roundSettings.setNrOfLikedCards(1);
        roundSettings.setNrOfSelectedCards(4);
        playerRound.setRoundSettings(roundSettings);

        playerRound.setLikedCards(
            new ArrayList<>(List.of(new Card(UUID.randomUUID(), new ArrayList<>(), true)))
        );
        playerRound.setSelectedCards(
            new ArrayList<>(
                List.of(
                    new Card(UUID.randomUUID(), new ArrayList<>(), true),
                    new Card(UUID.randomUUID(), new ArrayList<>(), true)
                )
            )
        );

        assertEquals(PlayerRoundPhase.PICKING, playerRound.getPhase());
    }

    //    @Test
    //    void getPhaseResult() {
    //        PlayerRound playerRound = spy(new PlayerRound());
    //        playerRound.setLikedCards(
    //            new ArrayList<>(
    //                List.of(
    //                    new Card(UUID.randomUUID(), new ArrayList<>()),
    //                    new Card(UUID.randomUUID(), new ArrayList<>())
    //                )
    //            )
    //        );
    //        playerRound.getRoundSettings().setNrOfLikedCards(2);
    //        playerRound.setSelectedCards(
    //            new ArrayList<>(
    //                List.of(
    //                    new Card(UUID.randomUUID(), new ArrayList<>()),
    //                    new Card(UUID.randomUUID(), new ArrayList<>()),
    //                    new Card(UUID.randomUUID(), new ArrayList<>())
    //                )
    //            )
    //        );
    //        playerRound.getRoundSettings().setNrOfSelectedCards(3);
    //        when(playerRound.hasDeterminateResult()).thenReturn(true);
    //
    //        assertEquals(PlayerRoundPhase.RESULT, playerRound.getPhase());
    //    }

    @Test
    void addLikedCardCorrectFlow() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setLikedCards(new ArrayList<>());
        Card card = new Card(UUID.randomUUID(), new ArrayList<>(), true);
        playerRound.setDistributedCards(
            new ArrayList<>(List.of(card, new Card(UUID.randomUUID(), new ArrayList<>(), true)))
        );

        playerRound.addLikedCard(card);

        assertEquals(1, playerRound.getLikedCards().size());
        assertEquals(card, playerRound.getLikedCards().get(0));
    }

    @Test
    void addLikedCardNotInHand() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setLikedCards(new ArrayList<>());
        Card card = new Card(UUID.randomUUID(), new ArrayList<>(), true);

        assertThrows(
            IllegalArgumentException.class,
            () -> playerRound.addLikedCard(card)
        );
    }

    @Test
    void addLikedCardAlreadyLiked() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setLikedCards(new ArrayList<>());
        Card card = new Card(UUID.randomUUID(), new ArrayList<>(), true);

        assertThrows(
            IllegalArgumentException.class,
            () -> playerRound.addLikedCard(card)
        );
    }

    @Test
    void addDislikedCardCorrectFlow() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setDislikedCards(new ArrayList<>());
        Card card = new Card(UUID.randomUUID(), new ArrayList<>(), true);
        playerRound.setDistributedCards(
            new ArrayList<>(List.of(card, new Card(UUID.randomUUID(), new ArrayList<>(), true)))
        );

        playerRound.addDislikedCard(card);

        assertEquals(1, playerRound.getDislikedCards().size());
        assertEquals(card, playerRound.getDislikedCards().get(0));
    }

    @Test
    void addDislikedCardNotInHand() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setDislikedCards(new ArrayList<>());
        Card card = new Card(UUID.randomUUID(), new ArrayList<>(), true);

        assertThrows(
            IllegalArgumentException.class,
            () -> playerRound.addDislikedCard(card)
        );
    }

    @Test
    void addDislikedCardAlreadyDisliked() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setDislikedCards(new ArrayList<>());
        Card card = new Card(UUID.randomUUID(), new ArrayList<>(), true);

        assertThrows(
            IllegalArgumentException.class,
            () -> playerRound.addDislikedCard(card)
        );
    }

    @Test
    void addSelectedCardsCorrectFlow() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setSelectedCards(new ArrayList<>());
        Card card1 = new Card(UUID.randomUUID(), new ArrayList<>(), true);
        Card card2 = new Card(UUID.randomUUID(), new ArrayList<>(), true);
        List<Card> cards = new ArrayList<>(List.of(card1, card2));
        playerRound.setDistributedCards(new ArrayList<>(List.of(card1, card2)));

        playerRound.addSelectedCards(cards);

        assertEquals(2, playerRound.getSelectedCards().size());
        assertEquals(card1, playerRound.getSelectedCards().get(0));
        assertEquals(card2, playerRound.getSelectedCards().get(1));
    }

    @Test
    void addSelectedCardsNotInHand() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setSelectedCards(new ArrayList<>());
        Card card1 = new Card(UUID.randomUUID(), new ArrayList<>(), true);
        Card card2 = new Card(UUID.randomUUID(), new ArrayList<>(), true);
        List<Card> cards = new ArrayList<>(List.of(card1, card2));

        assertThrows(
            IllegalArgumentException.class,
            () -> playerRound.addSelectedCards(cards)
        );
    }

    @Test
    void addSelectedCardsAlreadySelected() {
        PlayerRound playerRound = new PlayerRound();
        playerRound.setSelectedCards(new ArrayList<>());
        Card card1 = new Card(UUID.randomUUID(), new ArrayList<>(), true);
        Card card2 = new Card(UUID.randomUUID(), new ArrayList<>(), true);
        List<Card> cards = new ArrayList<>(List.of(card1, card2));
        playerRound.setSelectedCards(cards);

        assertThrows(
            IllegalArgumentException.class,
            () -> playerRound.addSelectedCards(cards)
        );
    }

    @Test
    void determineCardsChosenPerTypeWhenOneBlueAndTwoRedCards() {
        PlayerRound playerRound = new PlayerRound();
        Card cardBlue = new Card();
        Card cardRed1 = new Card();
        Card cardRed2 = new Card();
        List<Tag> tagsBlue = new ArrayList<>();
        tagsBlue.add(new Tag(TagType.COLOR, "Blauw"));
        List<Tag> tagsRed = new ArrayList<>();
        tagsRed.add(new Tag(TagType.COLOR, "Rood"));
        cardBlue.setTags(tagsBlue);
        cardRed1.setTags(tagsRed);
        cardRed2.setTags(tagsRed);
        List<Card> cards = new ArrayList<>();
        cards.add(cardBlue);
        cards.add(cardRed1);
        cards.add(cardRed2);
        playerRound.setSelectedCards(cards);

        Map<String, Integer> result = playerRound.determineCardsChosenPerType();

        assertEquals("Rood", result.keySet().toArray()[0]);
        assertEquals(2, result.values().toArray()[0]);

        assertEquals("Blauw", result.keySet().toArray()[1]);
        assertEquals(1, result.values().toArray()[1]);

        assertEquals(2, result.size());
    }

    @Test
    void determineCardsChosenPerTypeWhenTwoBlueAndTwoRedCards() {
        PlayerRound playerRound = new PlayerRound();
        Card cardBlue1 = new Card();
        Card cardBlue2 = new Card();
        Card cardRed1 = new Card();
        Card cardRed2 = new Card();
        List<Tag> tagsBlue = new ArrayList<>();
        tagsBlue.add(new Tag(TagType.COLOR, "Blauw"));
        List<Tag> tagsRed = new ArrayList<>();
        tagsRed.add(new Tag(TagType.COLOR, "Rood"));
        cardBlue1.setTags(tagsBlue);
        cardBlue2.setTags(tagsBlue);
        cardRed1.setTags(tagsRed);
        cardRed2.setTags(tagsRed);
        List<Card> cards = new ArrayList<>();
        cards.add(cardBlue1);
        cards.add(cardBlue2);
        cards.add(cardRed1);
        cards.add(cardRed2);
        playerRound.setSelectedCards(cards);

        Map<String, Integer> result = playerRound.determineCardsChosenPerType();

        assertEquals("Rood", result.keySet().toArray()[0]);
        assertEquals(2, result.values().toArray()[0]);

        assertEquals("Blauw", result.keySet().toArray()[1]);
        assertEquals(2, result.values().toArray()[1]);

        assertEquals(2, result.size());
    }

    @Test
    void getTopResultWhenTwoBlueAndOneRedCard() {
        PlayerRound playerRound = new PlayerRound();
        Card cardBlue1 = new Card();
        Card cardBlue2 = new Card();
        Card cardRed1 = new Card();
        List<Tag> tagsBlue = new ArrayList<>();
        tagsBlue.add(new Tag(TagType.COLOR, "Blauw"));
        List<Tag> tagsRed = new ArrayList<>();
        tagsRed.add(new Tag(TagType.COLOR, "Rood"));
        cardBlue1.setTags(tagsBlue);
        cardBlue2.setTags(tagsBlue);
        cardRed1.setTags(tagsRed);
        List<Card> cards = new ArrayList<>();
        cards.add(cardBlue1);
        cards.add(cardBlue2);
        cards.add(cardRed1);
        playerRound.setSelectedCards(cards);
        Map<String, Integer> result = playerRound.determineCardsChosenPerType();

        List<String> topResult = playerRound.getTopResultCardTypes(result);

        assertEquals(1, topResult.size());
        assertEquals("Blauw", topResult.get(0));
    }
}
