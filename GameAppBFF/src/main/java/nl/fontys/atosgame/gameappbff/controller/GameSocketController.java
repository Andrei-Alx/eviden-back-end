package nl.fontys.atosgame.gameappbff.controller;

import java.util.List;
import java.util.UUID;
import nl.fontys.atosgame.gameappbff.dto.*;
import nl.fontys.atosgame.gameappbff.model.Card;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.model.PlayerRound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Collection of game websockets for game related sockets:
 * - cardLiked
 * - cardDisliked
 * - cardsSelected
 * - playerPhase
 * - cardsDistributed
 * - lobby
 * - roundStarted
 * @author Aniek
 */
@Controller
public class GameSocketController {

    @Autowired
    private SimpMessagingTemplate template;

    public GameSocketController(@Autowired SimpMessagingTemplate template) {
        this.template = template;
    }

    /**
     * S-6
     * Send a message to the game that a card has been liked
     * @param gameId the id of the game
     * @param playerId the id of the player
     * @param card the card that has been liked
     * @return
     */
    public CardLikedDto cardLiked(UUID gameId, UUID playerId, Card card) {
        CardLikedDto cardLikedDto = new CardLikedDto(playerId, card);
        template.convertAndSend(
            String.format("/socket/gameapp/%s/cardLiked", gameId),
            cardLikedDto
        );
        return cardLikedDto;
    }

    /**
     * S-7
     * Send a message to the game that a card has been disliked
     * @param gameId the id of the game
     * @param playerId the id of the player
     * @param card the card that has been disliked
     * @return
     */
    public CardDislikedDto cardDisliked(UUID gameId, UUID playerId, Card card) {
        CardDislikedDto cardDislikedDto = new CardDislikedDto(playerId, card);
        template.convertAndSend(
            String.format("/socket/gameapp/%s/cardDisliked", gameId),
            cardDislikedDto
        );
        return cardDislikedDto;
    }

    /**
     * S-8
     * Send a message to the game that the cards have been selected
     * @param gameId the id of the game
     * @param playerId the id of the player
     * @param cards the cards that have been selected
     * @return
     */
    public CardsDto cardsSelected(UUID gameId, UUID playerId, List<Card> cards) {
        CardsDto cardsDto = new CardsDto(playerId, cards);
        template.convertAndSend(
            String.format("/socket/gameapp/%s/cardsSelected", gameId),
            cardsDto
        );
        return cardsDto;
    }

    /**
     * S-9
     * Send a message to the game that the player has changed phase
     * @param gameId The id of the game
     * @param playerRound The playerRound that has changed phase
     * @param phaseNumber The phase number
     */
    public PlayerPhaseDto playerPhase(
        UUID gameId,
        int phaseNumber,
        PlayerRound playerRound
    ) {
        PlayerPhaseDto playerPhaseDto = new PlayerPhaseDto(playerRound, phaseNumber);
        template.convertAndSend(
            String.format("/socket/gameapp/%s/playerPhase", gameId),
            playerPhaseDto
        );
        return playerPhaseDto;
    }

    /**
     * S-11
     * Send a message to the game that the cards have been distributed
     * @return
     */
    public CardsDto cardsDistributed(UUID gameId, UUID playerId, List<Card> cards) {
        CardsDto cardsDto = new CardsDto(playerId, cards);
        template.convertAndSend(
            String.format("/socket/gameapp/%s/cardsDistributed", gameId),
            cardsDto
        );
        return cardsDto;
    }

    /**
     * S-12
     * Send a message to the game that the lobby has been created
     * @param lobby
     * @return
     */
    public Lobby lobbyCreated(UUID gameId, Lobby lobby) {
        template.convertAndSend(String.format("/socket/gameapp/%s/lobby", gameId), lobby);
        return lobby;
    }

    /**
     * S-13
     * Send a message to the game that the round has started
     * @param roundId
     * @return
     */
    public UUID roundStarted(UUID gameId, UUID roundId) {
        template.convertAndSend(
            String.format("/socket/gameapp/%s/roundStarted", gameId),
            roundId
        );
        return roundId;
    }

    /**
     * S-14
     * Send a message to the game that the round has ended
     * @param gameId The id of the game
     * @param roundId The id of the round
     * @return The id of the round
     */
    public UUID roundEnded(UUID gameId, UUID roundId) {
        template.convertAndSend(
            String.format("/socket/gameapp/%s/roundEnded", gameId),
            roundId
        );
        return roundId;
    }

    /**
     * S-15
     * Send a message to the game that the game has started
     * @param gameId The id of the game
     * @return The id of the game
     */
    public UUID gameStarted(UUID gameId) {
        template.convertAndSend(
            String.format("/socket/gameapp/%s/gameStarted", gameId),
            gameId
        );
        return gameId;
    }
}
