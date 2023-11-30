package nl.fontys.atosgame.cardservice.controller;

import java.util.List;
import java.util.function.Function;

import nl.fontys.atosgame.cardservice.event.produced.CardSetEvent;
import nl.fontys.atosgame.cardservice.service.CardSetEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

/**
 * Collection of all event producers for card set events:
 * - CardSetCreated
 * - CardSetUpdated
 * - CardSetDeleted
 * @author Eli
 */
@Controller
public class CardSetEventProducers {

    private CardSetEventService cardSetEventService;

    public CardSetEventProducers(@Autowired CardSetEventService cardSetEventService) {
        this.cardSetEventService = cardSetEventService;
    }

    /**
     * Id: P-1
     * function to produce a CardSetEvent event
     * input topic: -
     * output topic: card-set-topic
     */
    @Bean
    public Function<CardSetEvent, Message<CardSetEvent>> produceCardSet() {
        return input -> {
            cardSetEventService.produceCardSet(input);
            return null;
        };
    }
}
