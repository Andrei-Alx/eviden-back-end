package nl.fontys.atosgame.roundservice.controller;


import nl.fontys.atosgame.roundservice.dto.CardLikeRequestDto;
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
        System.out.println("Like card");
    }
}
