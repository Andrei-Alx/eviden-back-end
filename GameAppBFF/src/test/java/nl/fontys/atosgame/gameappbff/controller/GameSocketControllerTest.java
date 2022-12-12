package nl.fontys.atosgame.gameappbff.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import nl.fontys.atosgame.gameappbff.dto.CardDislikedDto;
import nl.fontys.atosgame.gameappbff.dto.CardLikedDto;
import nl.fontys.atosgame.gameappbff.dto.CardsDto;
import nl.fontys.atosgame.gameappbff.dto.PlayerPhaseDto;
import nl.fontys.atosgame.gameappbff.model.Card;
import nl.fontys.atosgame.gameappbff.model.PlayerRound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.simp.SimpMessagingTemplate;

class GameSocketControllerTest {

    private SimpMessagingTemplate simpMessagingTemplate;
    private GameSocketController gameSocketController;

    @BeforeEach
    void setUp() {
        simpMessagingTemplate = mock(SimpMessagingTemplate.class);
        gameSocketController = new GameSocketController(simpMessagingTemplate);
    }

    @Test
    void playerPhase() {
        UUID gameId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        PlayerRound playerRound = new PlayerRound(
            UUID.fromString("00000000-0000-0000-0000-000000000000"),
            UUID.fromString("00000000-0000-0000-0000-000000000000"),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        );
        int phaseNumber = 1;

        gameSocketController.playerPhase(gameId, phaseNumber, playerRound);

        verify(simpMessagingTemplate)
            .convertAndSend(
                "/socket/gameapp/00000000-0000-0000-0000-000000000000/playerPhase",
                new PlayerPhaseDto(playerRound, phaseNumber)
            );
    }

    @Test
    void cardsDistributed() {
        UUID gameId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        UUID playerId = UUID.randomUUID();
        List<Card> cards = new ArrayList<>();

        gameSocketController.cardsDistributed(gameId, playerId, cards);

        verify(simpMessagingTemplate)
            .convertAndSend(
                "/socket/gameapp/00000000-0000-0000-0000-000000000000/cardsDistributed",
                new CardsDto(playerId, cards)
            );
    }

    @Test
    void cardLiked() {
        UUID gameId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        UUID playerId = UUID.randomUUID();
        Card card = new Card();

        gameSocketController.cardLiked(gameId, playerId, card);

        verify(simpMessagingTemplate)
            .convertAndSend(
                "/socket/gameapp/00000000-0000-0000-0000-000000000000/cardLiked",
                new CardLikedDto(playerId, card)
            );
    }

    @Test
    void cardDisliked() {
        UUID gameId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        UUID playerId = UUID.randomUUID();
        Card card = new Card();

        gameSocketController.cardDisliked(gameId, playerId, card);

        verify(simpMessagingTemplate)
            .convertAndSend(
                "/socket/gameapp/00000000-0000-0000-0000-000000000000/cardDisliked",
                new CardDislikedDto(playerId, card)
            );
    }

    @Test
    void cardsSelected() {
        UUID gameId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        UUID playerId = UUID.randomUUID();
        List<Card> cards = new ArrayList<>();

        gameSocketController.cardsSelected(gameId, playerId, cards);

        verify(simpMessagingTemplate)
            .convertAndSend(
                "/socket/gameapp/00000000-0000-0000-0000-000000000000/cardsSelected",
                new CardsDto(playerId, cards)
            );
    }
}
