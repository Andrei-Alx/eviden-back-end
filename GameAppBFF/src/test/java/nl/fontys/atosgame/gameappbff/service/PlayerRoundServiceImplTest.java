package nl.fontys.atosgame.gameappbff.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.gameappbff.controller.GameSocketController;
import nl.fontys.atosgame.gameappbff.model.Card;
import nl.fontys.atosgame.gameappbff.model.PlayerRound;
import nl.fontys.atosgame.gameappbff.model.Round;
import nl.fontys.atosgame.gameappbff.repository.PlayerRoundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerRoundServiceImplTest {

    private PlayerRoundRepository playerRoundRepository;
    private RoundService roundService;
    private CardService cardService;
    private GameSocketController gameSocketController;
    private PlayerRoundServiceImpl playerRoundService;

    @BeforeEach
    void setUp() {
        playerRoundRepository = mock(PlayerRoundRepository.class);
        roundService = mock(RoundService.class);
        gameSocketController = mock(GameSocketController.class);
        cardService = mock(CardService.class);
        playerRoundService =
            spy(
                new PlayerRoundServiceImpl(
                    playerRoundRepository,
                    roundService,
                    gameSocketController,
                    cardService
                )
            );
    }

    @Test
    void createPlayerRound() {
        UUID playerId = UUID.randomUUID();
        UUID roundId = UUID.randomUUID();
        when(playerRoundRepository.save(any(PlayerRound.class)))
            .thenAnswer(i -> {
                PlayerRound playerRound = i.getArgument(0);
                playerRound.setId(UUID.randomUUID());
                return playerRound;
            });

        PlayerRound playerRound = playerRoundService.createPlayerRound(playerId, roundId);

        assertEquals(playerId, playerRound.getPlayerId());
        verify(playerRoundRepository).save(playerRound);
        verify(roundService).addPlayerRound(roundId, playerRound);
    }

    @Test
    void startPhaseDoesNotExist() {
        //        UUID playerId = UUID.randomUUID();
        //        UUID roundId = UUID.randomUUID();
        //        UUID gameID = UUID.randomUUID();
        //        int phase = 1;
        //        Round round = new Round();
        //        when(roundService.getRound(roundId)).thenReturn(Optional.of(round));
        //        doReturn(new PlayerRound())
        //            .when(playerRoundService)
        //            .createPlayerRound(playerId, roundId);
        //
        //        playerRoundService.startPhase(playerId, roundId, gameID, phase);
        //
        //        assertEquals(1, round.getPlayerRounds().size());
        //        assertEquals(new PlayerRound(), round.getPlayerRounds().get(0));
        //        verify(playerRoundRepository).save(any(PlayerRound.class));
        //        verify(gameSocketController).playerPhase(gameID, playerId, phase);
    }

    @Test
    void startPhaseExists() {
        UUID playerId = UUID.randomUUID();
        UUID roundId = UUID.randomUUID();
        UUID gameID = UUID.randomUUID();
        int phase = 1;
        Round round = new Round();
        PlayerRound playerRound = new PlayerRound();
        playerRound.setPlayerId(playerId);
        round.getPlayerRounds().add(playerRound);
        when(roundService.getRoundById(roundId)).thenReturn(Optional.of(round));

        playerRoundService.startPhase(playerId, roundId, gameID, phase);

        assertEquals(1, round.getPlayerRounds().size());
        assertEquals(playerRound, round.getPlayerRounds().get(0));
        verify(playerRoundRepository).save(any(PlayerRound.class));
        verify(gameSocketController).playerPhase(gameID, phase, playerRound);
    }

    @Test
    void endPhase() {
        UUID playerId = UUID.randomUUID();
        UUID roundId = UUID.randomUUID();
        UUID gameID = UUID.randomUUID();
        int phase = 1;
        Round round = new Round();
        PlayerRound playerRound = new PlayerRound();
        playerRound.setPlayerId(playerId);
        round.getPlayerRounds().add(playerRound);
        when(roundService.getRoundById(roundId)).thenReturn(Optional.of(round));

        playerRoundService.endPhase(playerId, roundId, gameID, phase);

        verify(playerRoundRepository).save(any(PlayerRound.class));
    }

    @Test
    void getPlayerRoundExists() {
        PlayerRound playerRound1 = new PlayerRound();
        playerRound1.setPlayerId(UUID.randomUUID());
        PlayerRound playerRound2 = new PlayerRound();
        playerRound2.setPlayerId(UUID.randomUUID());
        UUID roundId = UUID.randomUUID();
        Round round = new Round();
        round.getPlayerRounds().add(playerRound1);
        round.getPlayerRounds().add(playerRound2);
        when(roundService.getRoundById(roundId)).thenReturn(Optional.of(round));

        Optional<PlayerRound> playerRound = playerRoundService.getPlayerRound(
            playerRound2.getPlayerId(),
            roundId
        );

        assertTrue(playerRound.isPresent());
        assertEquals(playerRound2, playerRound.get());
    }

    @Test
    void getPlayerRoundDoesNotExist() {
        PlayerRound playerRound1 = new PlayerRound();
        playerRound1.setPlayerId(UUID.randomUUID());
        PlayerRound playerRound2 = new PlayerRound();
        playerRound2.setPlayerId(UUID.randomUUID());
        UUID roundId = UUID.randomUUID();
        Round round = new Round();
        round.getPlayerRounds().add(playerRound1);
        round.getPlayerRounds().add(playerRound2);
        when(roundService.getRoundById(roundId)).thenReturn(Optional.of(round));

        Optional<PlayerRound> playerRound = playerRoundService.getPlayerRound(
            UUID.randomUUID(),
            roundId
        );

        assertFalse(playerRound.isPresent());
    }

    @Test
    void distributeCards() {
        UUID playerId = UUID.randomUUID();
        UUID roundId = UUID.randomUUID();
        UUID gameID = UUID.randomUUID();
        List<UUID> cardIds = new ArrayList<>();
        List<Card> cards = new ArrayList<>();
        PlayerRound playerRound = new PlayerRound();
        doReturn(Optional.of(playerRound))
            .when(playerRoundService)
            .getPlayerRound(playerId, roundId);
        doReturn(cards).when(cardService).getCards(cardIds);
        when(playerRoundRepository.save(any(PlayerRound.class)))
            .thenAnswer(i -> i.getArgument(0));

        PlayerRound result = playerRoundService.distributeCards(
            playerId,
            roundId,
            gameID,
            cardIds
        );

        verify(playerRoundRepository).save(result);
        assertEquals(cards, result.getDistributedCards());
        verify(gameSocketController).cardsDistributed(gameID, playerId, cards);
    }

    @Test
    void likeCard() {
        UUID playerId = UUID.randomUUID();
        UUID roundId = UUID.randomUUID();
        UUID gameID = UUID.randomUUID();
        UUID cardId = UUID.randomUUID();
        PlayerRound playerRound = new PlayerRound();
        playerRound.setPlayerId(playerId);
        doReturn(Optional.of(playerRound))
            .when(playerRoundService)
            .getPlayerRound(playerId, roundId);
        Card card = new Card();
        card.setId(cardId);
        doReturn(Optional.of(card)).when(cardService).getCard(cardId);
        when(playerRoundRepository.save(any(PlayerRound.class)))
            .thenAnswer(i -> i.getArgument(0));

        playerRoundService.likeCard(playerId, roundId, gameID, cardId);

        verify(playerRoundRepository).save(playerRound);
        assertEquals(1, playerRound.getLikedCards().size());
        assertEquals(cardId, playerRound.getLikedCards().get(0).getId());
        verify(gameSocketController).cardLiked(gameID, playerId, card);
    }

    @Test
    void dislikeCard() {
        UUID playerId = UUID.randomUUID();
        UUID roundId = UUID.randomUUID();
        UUID gameID = UUID.randomUUID();
        UUID cardId = UUID.randomUUID();
        PlayerRound playerRound = new PlayerRound();
        playerRound.setPlayerId(playerId);
        doReturn(Optional.of(playerRound))
            .when(playerRoundService)
            .getPlayerRound(playerId, roundId);
        Card card = new Card();
        card.setId(cardId);
        doReturn(Optional.of(card)).when(cardService).getCard(cardId);
        when(playerRoundRepository.save(any(PlayerRound.class)))
            .thenAnswer(i -> i.getArgument(0));

        playerRoundService.dislikeCard(playerId, roundId, gameID, cardId);

        verify(playerRoundRepository).save(playerRound);
        assertEquals(1, playerRound.getDislikedCards().size());
        assertEquals(cardId, playerRound.getDislikedCards().get(0).getId());
        verify(gameSocketController).cardDisliked(gameID, playerId, card);
    }

    @Test
    void selectCards() {
        UUID playerId = UUID.randomUUID();
        UUID roundId = UUID.randomUUID();
        UUID gameID = UUID.randomUUID();
        List<UUID> cardIds = new ArrayList<>();
        List<Card> cards = new ArrayList<>();
        PlayerRound playerRound = new PlayerRound();
        doReturn(Optional.of(playerRound))
            .when(playerRoundService)
            .getPlayerRound(playerId, roundId);
        doReturn(cards).when(cardService).getCards(cardIds);
        when(playerRoundRepository.save(any(PlayerRound.class)))
            .thenAnswer(i -> i.getArgument(0));

        PlayerRound result = playerRoundService.selectCards(
            playerId,
            roundId,
            gameID,
            cardIds
        );

        verify(playerRoundRepository).save(result);
        assertEquals(cards, result.getSelectedCards());
        verify(gameSocketController).cardsSelected(gameID, playerId, cards);
    }
}
