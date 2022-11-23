package nl.fontys.atosgame.roundservice.controller;

import nl.fontys.atosgame.roundservice.dto.CardDislikeRequestDto;
import nl.fontys.atosgame.roundservice.dto.CardLikeRequestDto;
import nl.fontys.atosgame.roundservice.dto.CardSubmitRequestDto;
import nl.fontys.atosgame.roundservice.service.RoundService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CardSocketControllerTest {

    private RoundService roundService;
    private CardSocketController cardSocketController;

    @BeforeEach
    void setUp() {
        roundService = mock(RoundService.class);
        cardSocketController = new CardSocketController(roundService);
    }

    @Test
    void likeCard() {
        UUID gameId = UUID.randomUUID();
        UUID roundId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();
        UUID cardId = UUID.randomUUID();
        CardLikeRequestDto cardLikeRequestDto = new CardLikeRequestDto(
                playerId,
                cardId,
                roundId,
                gameId
        );

        cardSocketController.likeCard(cardLikeRequestDto);

        verify(roundService).likeCard(playerId, cardId, gameId, roundId);
    }

    @Test
    void dislikeCard() {
        UUID gameId = UUID.randomUUID();
        UUID roundId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();
        UUID cardId = UUID.randomUUID();
        CardDislikeRequestDto cardDislikeRequestDto = new CardDislikeRequestDto(
                playerId,
                cardId,
                roundId,
                gameId
        );

        cardSocketController.dislikeCard(cardDislikeRequestDto);

        verify(roundService).dislikeCard(playerId, cardId, gameId, roundId);
    }

    @Test
    void submitCards() {
        UUID gameId = UUID.randomUUID();
        UUID roundId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();
        List<UUID> cardIds = mock(List.class);
        CardSubmitRequestDto cardSubmitRequestDto = new CardSubmitRequestDto(
                playerId,
                cardIds,
                roundId,
                gameId
        );

        cardSocketController.selectCards(cardSubmitRequestDto);

        verify(roundService).selectCards(playerId, cardIds, gameId, roundId);

    }
}