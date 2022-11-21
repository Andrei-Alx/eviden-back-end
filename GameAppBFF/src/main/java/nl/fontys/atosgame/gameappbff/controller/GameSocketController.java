package nl.fontys.atosgame.gameappbff.controller;

import java.util.UUID;
import nl.fontys.atosgame.gameappbff.dto.*;
import nl.fontys.atosgame.gameappbff.model.Lobby;
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

    /**
     * S-6
     * Send a message to the game that a card has been liked
     * @param cardLikedDto
     * @return
     */
    public CardLikedDto cardLiked(String gameId, CardLikedDto cardLikedDto) {
        //TODO
        template.convertAndSend(
            String.format("/socket/gameapp/%s/cardLiked", gameId),
            cardLikedDto
        );
        return cardLikedDto;
    }

    /**
     * S-7
     * Send a message to the game that a card has been disliked
     * @param cardDislikedDto
     * @return
     */
    public CardDislikedDto cardDisliked(String gameId, CardDislikedDto cardDislikedDto) {
        //TODO
        template.convertAndSend(
            String.format("/socket/gameapp/%s/cardDisliked", gameId),
            cardDislikedDto
        );
        return cardDislikedDto;
    }

    /**
     * S-8
     * Send a message to the game that the cards have been selected
     * @param cardsDto
     * @return
     */
    public CardsDto cardsSelected(String gameId, CardsDto cardsDto) {
        //TODO
        template.convertAndSend(
            String.format("/socket/gameapp/%s/cardsSelected", gameId),
            cardsDto
        );
        return cardsDto;
    }

    /**
     * S-9
     * Send a message to the game that the player has changed phase
     * @param playerPhaseDto
     * @return
     */
    public PlayerPhaseDto playerPhase(String gameId, PlayerPhaseDto playerPhaseDto) {
        //TODO
        template.convertAndSend(
            String.format("/socket/gameapp/%s/playerPhase", gameId),
            playerPhaseDto
        );
        return playerPhaseDto;
    }

    /**
     * S-11
     * Send a message to the game that the cards have been distributed
     * @param cardsDto
     * @return
     */
    public CardsDto cardsDistributed(String gameId, CardsDto cardsDto) {
        //TODO
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
    public Lobby lobby(String gameId, Lobby lobby) {
        //TODO
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
}
