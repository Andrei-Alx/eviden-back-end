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
    private CardService cardService;
    private PlayerRoundRepository playerRoundRepository;
    private PlayerRoundServiceImpl playerRoundService;

    @BeforeEach
    void setUp() {
        cardService = mock(CardService.class);
        playerRoundRepository = mock(PlayerRoundRepository.class);
        playerRoundService = spy(new PlayerRoundServiceImpl(cardService, playerRoundRepository));
    }

    @Test
    void likeCard() {
        UUID cardId = UUID.randomUUID();
        PlayerRound playerRound = new PlayerRound();
        Card card = new Card();
        card.setId(cardId);
        playerRound.setDistributedCards(List.of(card));
        when(cardService.getCard(cardId)).thenReturn(Optional.of(card));
        when(playerRoundRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        PlayerRound result = playerRoundService.likeCard(playerRound, cardId);

        assertEquals(playerRound, result);
        assertTrue(playerRound.getLikedCards().contains(card));
        verify(playerRoundRepository).save(playerRound);
        fail();
    }

    @Test
    void dislikeCard() {
        UUID cardId = UUID.randomUUID();
        PlayerRound playerRound = new PlayerRound();
        Card card = new Card();
        card.setId(cardId);
        playerRound.setDistributedCards(List.of(card));
        when(cardService.getCard(cardId)).thenReturn(Optional.of(card));
        when(playerRoundRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        PlayerRound result = playerRoundService.dislikeCard(playerRound, cardId);

        assertEquals(playerRound, result);
        assertTrue(playerRound.getDislikedCards().contains(card));
        verify(playerRoundRepository).save(playerRound);
        fail();
    }

    @Test
    void selectCards() {
        UUID cardId1 = UUID.randomUUID();
        UUID cardId2 = UUID.randomUUID();
        List<UUID> cardIds = List.of(cardId1, cardId2);
        PlayerRound playerRound = new PlayerRound();
        Card card1 = new Card();
        card1.setId(cardId1);
        Card card2 = new Card();
        card2.setId(cardId2);
        playerRound.setDistributedCards(List.of(card1, card2));
        when(cardService.getCards(cardIds)).thenReturn(List.of(card1, card2));
        when(playerRoundRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        PlayerRound result = playerRoundService.selectCards(playerRound, cardIds);

        assertEquals(playerRound, result);
        assertTrue(playerRound.getSelectedCards().containsAll(List.of(card1, card2)));
        verify(playerRoundRepository).save(playerRound);
        fail();
    }
}