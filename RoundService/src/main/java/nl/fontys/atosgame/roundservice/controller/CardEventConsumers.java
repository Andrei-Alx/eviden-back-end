package nl.fontys.atosgame.roundservice.controller;

import java.util.function.Function;

import nl.fontys.atosgame.roundservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

/**
 * Collection of all event consumers for card events:
 * - CardCreated
 * - CardUpdated
 * - CardDeleted
 * @author Eli
 */
@Controller
public class CardEventConsumers {

    private CardService cardService;

    public CardEventConsumers(@Autowired CardService cardService) {
        this.cardService = cardService;
    }

}
