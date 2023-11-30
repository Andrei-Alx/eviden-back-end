package nl.fontys.atosgame.gameappbff.controller;

import java.util.function.Function;
import nl.fontys.atosgame.gameappbff.event.consumed.CardCreatedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.CardDeletedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.CardUpdatedEvent;
import nl.fontys.atosgame.gameappbff.model.Card;
import nl.fontys.atosgame.gameappbff.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

/**
 * Collection of consumers for card related events:
 * - CardCreated
 * - CardUpdated
 * - CardDeleted
 * @author Aniek
 */
@Controller
public class CardConsumers {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardConsumers.class);
    private CardService cardService;

    public CardConsumers(@Autowired CardService cardService) {
        this.cardService = cardService;
    }

}
