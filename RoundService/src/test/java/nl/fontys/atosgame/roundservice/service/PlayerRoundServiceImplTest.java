package nl.fontys.atosgame.roundservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.dto.*;
import nl.fontys.atosgame.roundservice.enums.PlayerRoundPhase;
import nl.fontys.atosgame.roundservice.event.produced.PlayerDislikedCard;
import nl.fontys.atosgame.roundservice.event.produced.PlayerLikedCard;
import nl.fontys.atosgame.roundservice.model.Card;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import nl.fontys.atosgame.roundservice.repository.PlayerRoundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.ApplicationEventPublisher;

class PlayerRoundServiceImplTest {

    private CardService cardService;
    private StreamBridge streamBridge;
    private ApplicationEventPublisher eventPublisher;
    private PlayerRoundRepository playerRoundRepository;
    private PlayerRoundServiceImpl playerRoundService;

    @BeforeEach
    void setUp() {
        cardService = mock(CardService.class);
        streamBridge = mock(StreamBridge.class);
        eventPublisher = mock(ApplicationEventPublisher.class);
        playerRoundRepository = mock(PlayerRoundRepository.class);
        playerRoundService =
            spy(
                new PlayerRoundServiceImpl(
                    cardService,
                    streamBridge,
                    eventPublisher,
                    playerRoundRepository
                )
            );
    }

    @Test
    void likeCard() {
        UUID gameId = UUID.randomUUID();
        UUID roundId = UUID.randomUUID();
        UUID cardId = UUID.randomUUID();
        PlayerRound playerRound = new PlayerRound();
        Card card = new Card();
        card.setId(cardId);
        playerRound.setDistributedCards(List.of(card));
        when(cardService.getCard(cardId)).thenReturn(Optional.of(card));
        when(playerRoundRepository.save(any()))
            .thenAnswer(invocation -> invocation.getArgument(0));

        PlayerRound result = playerRoundService.likeCard(
            playerRound,
            cardId,
            gameId,
            roundId
        );

        assertEquals(playerRound, result);
        assertTrue(playerRound.getLikedCards().contains(card));
        verify(playerRoundRepository).save(playerRound);
        streamBridge.send(
            "producePlayerLikedCard-in-0",
            new PlayerLikedCard(playerRound.getPlayerId(), gameId, roundId, cardId)
        );
    }

    @Test
    void likeCardWhenEndingPhaseProducesEvents() {
        UUID gameId = UUID.randomUUID();
        UUID roundId = UUID.randomUUID();
        UUID cardId = UUID.randomUUID();
        PlayerRound playerRound = mock(PlayerRound.class);
        when(playerRound.getPhase())
            .thenReturn(PlayerRoundPhase.LIKING, PlayerRoundPhase.PICKING);
        Card card = new Card();
        card.setId(cardId);
        playerRound.setDistributedCards(List.of(card));
        when(cardService.getCard(cardId)).thenReturn(Optional.of(card));
        when(playerRoundRepository.save(any()))
            .thenAnswer(invocation -> invocation.getArgument(0));

        playerRoundService.likeCard(playerRound, cardId, gameId, roundId);

        verify(streamBridge)
            .send(
                "producePlayerPhaseEnded-in-0",
                new PlayerPhaseEndedDto(0, playerRound.getPlayerId(), gameId, roundId)
            );
        verify(streamBridge)
            .send(
                "producePlayerPhaseStarted-in-0",
                new PlayerPhaseStartedDto(1, playerRound.getPlayerId(), gameId, roundId)
            );
    }

    @Test
    void dislikeCard() {
        UUID gameId = UUID.randomUUID();
        UUID roundId = UUID.randomUUID();
        UUID cardId = UUID.randomUUID();
        PlayerRound playerRound = new PlayerRound();
        Card card = new Card();
        card.setId(cardId);
        playerRound.setDistributedCards(List.of(card));
        when(cardService.getCard(cardId)).thenReturn(Optional.of(card));
        when(playerRoundRepository.save(any()))
            .thenAnswer(invocation -> invocation.getArgument(0));

        PlayerRound result = playerRoundService.dislikeCard(
            playerRound,
            cardId,
            gameId,
            roundId
        );

        assertEquals(playerRound, result);
        assertTrue(playerRound.getDislikedCards().contains(card));
        verify(playerRoundRepository).save(playerRound);
        streamBridge.send(
            "producePlayerDislikedCard-in-0",
            new PlayerDislikedCard(gameId, roundId, playerRound.getPlayerId(), cardId)
        );
    }

    @Test
    void selectCards() {
        UUID gameId = UUID.randomUUID();
        UUID roundId = UUID.randomUUID();
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
        when(playerRoundRepository.save(any()))
            .thenAnswer(invocation -> invocation.getArgument(0));

        PlayerRound result = playerRoundService.selectCards(
            playerRound,
            cardIds,
            gameId,
            roundId
        );

        assertEquals(playerRound, result);
        assertTrue(playerRound.getSelectedCards().containsAll(List.of(card1, card2)));
        verify(playerRoundRepository).save(playerRound);
        verify(streamBridge)
            .send(
                "producePlayerSelectedCards-in-0",
                new CardsSelectedEventDto(
                    playerRound.getPlayerId(),
                    cardIds,
                    roundId,
                    gameId
                )
            );
    }

    @Test
    void selectCardsWhenEndingPhaseProducesEvents() {
        UUID gameId = UUID.randomUUID();
        UUID roundId = UUID.randomUUID();
        UUID cardId1 = UUID.randomUUID();
        UUID cardId2 = UUID.randomUUID();
        List<UUID> cardIds = List.of(cardId1, cardId2);
        PlayerRound playerRound = mock(PlayerRound.class);
        when(playerRound.getPhase())
            .thenReturn(PlayerRoundPhase.PICKING, PlayerRoundPhase.RESULT);
        Card card1 = new Card();
        card1.setId(cardId1);
        Card card2 = new Card();
        card2.setId(cardId2);
        playerRound.setDistributedCards(List.of(card1, card2));
        when(cardService.getCards(cardIds)).thenReturn(List.of(card1, card2));
        when(playerRoundRepository.save(any()))
            .thenAnswer(invocation -> invocation.getArgument(0));

        playerRoundService.selectCards(playerRound, cardIds, gameId, roundId);

        verify(streamBridge)
            .send(
                "producePlayerPhaseEnded-in-0",
                new PlayerPhaseEndedDto(1, playerRound.getPlayerId(), gameId, roundId)
            );
        verify(streamBridge)
            .send(
                "producePlayerPhaseStarted-in-0",
                new PlayerPhaseStartedDto(2, playerRound.getPlayerId(), gameId, roundId)
            );
    }
}
