package nl.fontys.atosgame.gameappbff.controller;

import nl.fontys.atosgame.gameappbff.dto.*;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class GameSocketController {

    @Autowired
    private SimpMessagingTemplate template;

    public CardLikedDto cardLiked(String gameId, CardLikedDto cardLikedDto) {
        template.convertAndSend(String.format("/socket/gameapp/%s/cardLiked", gameId), cardLikedDto);
        return cardLikedDto;
    }

    public CardDislikedDto cardDisliked(String gameId, CardDislikedDto cardDislikedDto) {
        template.convertAndSend(String.format("/socket/gameapp/%s/cardDisliked", gameId), cardDislikedDto);
        return cardDislikedDto;
    }

    public CardsDto cardsSelected(String gameId, CardsDto cardsDto) {
        template.convertAndSend(String.format("/socket/gameapp/%s/cardsSelected", gameId), cardsDto);
        return cardsDto;
    }

    public PlayerPhaseDto playerPhase(String gameId, PlayerPhaseDto playerPhaseDto) {
        template.convertAndSend(String.format("/socket/gameapp/%s/playerPhase", gameId), playerPhaseDto);
        return playerPhaseDto;
    }

    public CardsDto cardsDistributed(String gameId, CardsDto cardsDto) {
        template.convertAndSend(String.format("/socket/gameapp/%s/cardsDistributed", gameId), cardsDto);
        return cardsDto;
    }

    public Lobby lobby(String gameId, Lobby lobby) {
        template.convertAndSend(String.format("/socket/gameapp/%s/lobby", gameId), lobby);
        return lobby;
    }

    public Lobby roundStarted(String gameId, Lobby lobby) {
        template.convertAndSend(String.format("/socket/gameapp/%s/roundStarted", gameId), lobby);
        return lobby;
    }
}
