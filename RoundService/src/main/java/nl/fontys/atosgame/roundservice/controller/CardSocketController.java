package nl.fontys.atosgame.roundservice.controller;

import nl.fontys.atosgame.roundservice.dto.CardDislikeRequestDto;
import nl.fontys.atosgame.roundservice.dto.CardLikeRequestDto;
import nl.fontys.atosgame.roundservice.dto.CardSubmitRequestDto;
import nl.fontys.atosgame.roundservice.service.interfaces.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * Controller for socket endpoints for card related events
 * @author Eli
 */
@Controller
public class CardSocketController {

    private final RoundService roundService;

    public CardSocketController(@Autowired RoundService roundService) {
        this.roundService = roundService;
    }

    /**
     * Id: S-1
     * Socket endpoint that consumes like requests
     * @param cardLikeRequestDto
     */
    @MessageMapping("/likeCard")
    public void likeCard(CardLikeRequestDto cardLikeRequestDto) {
        this.roundService.likeCard(
                cardLikeRequestDto.getPlayerId(),
                cardLikeRequestDto.getCardId(),
                cardLikeRequestDto.getGameId(),
                cardLikeRequestDto.getRoundId()
            );
    }

    /**
     * Id: S-2
     * Socket endpoint that consumes dislike requests
     */
    @MessageMapping("/dislikeCard")
    public void dislikeCard(CardDislikeRequestDto cardDislikeRequestDto) {
        this.roundService.dislikeCard(
                cardDislikeRequestDto.getPlayerId(),
                cardDislikeRequestDto.getCardId(),
                cardDislikeRequestDto.getGameId(),
                cardDislikeRequestDto.getRoundId()
            );
    }

    /**
     * Id: S-3
     * Socket endpoint that consumes card select requests
     */
    @MessageMapping("/submitCards")
    public void selectCards(CardSubmitRequestDto cardSubmitRequestDto) {
        this.roundService.selectCards(
                cardSubmitRequestDto.getPlayerId(),
                cardSubmitRequestDto.getCardIds(),
                cardSubmitRequestDto.getGameId(),
                cardSubmitRequestDto.getRoundId()
            );
    }
}
