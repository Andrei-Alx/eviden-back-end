package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.model.Card;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import nl.fontys.atosgame.roundservice.model.Round;
import nl.fontys.atosgame.roundservice.repository.PlayerRoundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerRoundServiceImplTest {

    private RoundService roundService;
    private CardService cardService;
    private PlayerRoundRepository playerRoundRepository;
    private PlayerRoundServiceImpl playerRoundService;

    @BeforeEach
    void setUp() {
        roundService = mock(RoundService.class);
        cardService = mock(CardService.class);
        playerRoundRepository = mock(PlayerRoundRepository.class);
        playerRoundService = spy(new PlayerRoundServiceImpl(roundService, cardService, playerRoundRepository));
    }

    @Test
    void getPlayerRound() {
        UUID roundId = UUID.randomUUID();
        Round round = new Round();
        round.setId(roundId);
        UUID playerId = UUID.randomUUID();
        PlayerRound playerRound = new PlayerRound();
        playerRound.setPlayerId(playerId);
        round.getPlayerRounds().add(playerRound);
        when(roundService.getRound(roundId)).thenReturn(Optional.of(round));

        PlayerRound result = playerRoundService.getPlayerRound(playerId, roundId);

        assertEquals(playerRound, result);
    }

    @Test
    void likeCard() {
        UUID roundId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();
        UUID cardId = UUID.randomUUID();
        PlayerRound playerRound = new PlayerRound();
        doReturn(playerRound).when(playerRoundService).getPlayerRound(playerId, roundId);
        Card card = new Card();
        card.setId(cardId);
        playerRound.setDistributedCards(List.of(card));
        when(cardService.getCard(cardId)).thenReturn(Optional.of(card));
        when(playerRoundRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        PlayerRound result = playerRoundService.likeCard(playerId, roundId, cardId);

        assertEquals(playerRound, result);
        assertTrue(playerRound.getLikedCards().contains(card));
        verify(playerRoundRepository).save(playerRound);
        fail();
    }

    @Test
    void dislikeCard() {
        UUID roundId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();
        UUID cardId = UUID.randomUUID();
        PlayerRound playerRound = new PlayerRound();
        doReturn(playerRound).when(playerRoundService).getPlayerRound(playerId, roundId);
        Card card = new Card();
        card.setId(cardId);
        playerRound.setDistributedCards(List.of(card));
        when(cardService.getCard(cardId)).thenReturn(Optional.of(card));
        when(playerRoundRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        PlayerRound result = playerRoundService.dislikeCard(playerId, roundId, cardId);

        assertEquals(playerRound, result);
        assertTrue(playerRound.getDislikedCards().contains(card));
        verify(playerRoundRepository).save(playerRound);
        fail();
    }

    @Test
    void selectCards() {
        UUID roundId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();
        UUID cardId1 = UUID.randomUUID();
        UUID cardId2 = UUID.randomUUID();
        List<UUID> cardIds = List.of(cardId1, cardId2);
        PlayerRound playerRound = new PlayerRound();
        doReturn(playerRound).when(playerRoundService).getPlayerRound(playerId, roundId);
        Card card1 = new Card();
        card1.setId(cardId1);
        Card card2 = new Card();
        card2.setId(cardId2);
        playerRound.setDistributedCards(List.of(card1, card2));
        when(cardService.getCards(cardIds)).thenReturn(List.of(card1, card2));
        when(playerRoundRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        PlayerRound result = playerRoundService.selectCards(playerId, roundId, cardIds);

        assertEquals(playerRound, result);
        assertTrue(playerRound.getSelectedCards().containsAll(List.of(card1, card2)));
        verify(playerRoundRepository).save(playerRound);
        fail();
    }
}