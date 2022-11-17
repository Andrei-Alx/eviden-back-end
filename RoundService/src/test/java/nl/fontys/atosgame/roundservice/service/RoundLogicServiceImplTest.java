package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.enums.RoundStatus;
import nl.fontys.atosgame.roundservice.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class RoundLogicServiceImplTest {

    private CardShuffler cardShuffler;
    private RoundLogicServiceImpl roundLogicService;

    @BeforeEach
    void setUp() {
        cardShuffler = mock(CardShuffler.class);
        roundLogicService = new RoundLogicServiceImpl(cardShuffler);
    }

    @Test
    void initializeRound() {
        Round round = new Round(
                UUID.randomUUID(),
                new ArrayList<>(),
                RoundStatus.CREATED,
                null
        );
        List<UUID> playerIds = new ArrayList<>(List.of(UUID.randomUUID(), UUID.randomUUID()));

        Round result = roundLogicService.initializeRound(round, playerIds);

        assertEquals(2, result.getPlayerRounds().size());
        assertEquals(playerIds.get(0), result.getPlayerRounds().get(0).getPlayerId());
        assertEquals(playerIds.get(1), result.getPlayerRounds().get(1).getPlayerId());
    }

    @Test
    void distributeCardsSameOrder() {
        RoundSettings roundSettings = new RoundSettings();
        roundSettings.setShowSameCardOrder(true);
        CardSet cardSet = mock(CardSet.class);
        List<Card> cards = new ArrayList<>(List.of(mock(Card.class), mock(Card.class)));
        doReturn(cards).when(cardSet).getCards();
        roundSettings.setCardSet(cardSet);
        List<PlayerRound> playerRounds = new ArrayList<>(
                List.of(
                        new PlayerRound(UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()),
                        new PlayerRound(UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()),
                        new PlayerRound(UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>())
                )
        );
        Round round = new Round(
                UUID.randomUUID(),
                playerRounds,
                RoundStatus.CREATED,
                roundSettings
        );
        // Set behaviour of cardShuffler
        List<Card> cards1 = mock(List.class);
        List<Card> cards2 = mock(List.class);
        doReturn(cards1, cards2).when(cardShuffler).randomShuffle(anyCollection());

        Round result = roundLogicService.distributeCards(round);

        assertEquals(3, result.getPlayerRounds().size());
        assertEquals(cards1, result.getPlayerRounds().get(0).getDistributedCards());
        assertEquals(cards1, result.getPlayerRounds().get(1).getDistributedCards());
        assertEquals(cards1, result.getPlayerRounds().get(2).getDistributedCards());
    }

    @Test
    void distributeCardsDifferentOrder() {
        RoundSettings roundSettings = new RoundSettings();
        roundSettings.setShowSameCardOrder(false);
        CardSet cardSet = mock(CardSet.class);
        List<Card> cards = new ArrayList<>(List.of(mock(Card.class), mock(Card.class)));
        doReturn(cards).when(cardSet).getCards();
        roundSettings.setCardSet(cardSet);
        List<PlayerRound> playerRounds = new ArrayList<>(
                List.of(
                        new PlayerRound(UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()),
                        new PlayerRound(UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()),
                        new PlayerRound(UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>())
                )
        );
        Round round = new Round(
                UUID.randomUUID(),
                playerRounds,
                RoundStatus.CREATED,
                roundSettings
        );
        // Set behaviour of cardShuffler
        List<Card> cards1 = mock(List.class);
        List<Card> cards2 = mock(List.class);
        List<Card> cards3 = mock(List.class);
        doReturn(cards1, cards2, cards3).when(cardShuffler).randomShuffle(anyCollection());

        Round result = roundLogicService.distributeCards(round);

        assertEquals(3, result.getPlayerRounds().size());
        assertEquals(cards1, result.getPlayerRounds().get(0).getDistributedCards());
        assertEquals(cards2, result.getPlayerRounds().get(1).getDistributedCards());
        assertEquals(cards3, result.getPlayerRounds().get(2).getDistributedCards());
    }
}