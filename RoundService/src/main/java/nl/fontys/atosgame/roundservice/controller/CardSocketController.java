package nl.fontys.atosgame.roundservice.controller;

import nl.fontys.atosgame.roundservice.dto.CardDislikeRequestDto;
import nl.fontys.atosgame.roundservice.dto.CardLikeRequestDto;
import nl.fontys.atosgame.roundservice.dto.CardSubmitRequestDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * Controller for socket endpoints for card related events
 * @author Eli
 */
@Controller
public class CardSocketController {

    /**
     * Id: S-1
     * Socket endpoint that consumes like requests
     * @param cardLikeRequestDto
     */
    @MessageMapping("/likeCard")
    public void likeCard(CardLikeRequestDto cardLikeRequestDto) {
        // TODO: implement
    }

    /**
     * Id: S-2
     * Socket endpoint that consumes dislike requests
     */
    @MessageMapping("/dislikeCard")
    public void dislikeCard(CardDislikeRequestDto cardDislikeRequestDto) {
        // TODO: implement
    }

    /**
     * Id: S-3
     * Socket endpoint that consumes card submit requests
     */
    @MessageMapping("/submitCards")
    public void submitCards(CardSubmitRequestDto cardSubmitRequestDto) {
        // TODO: implement
    }
}
