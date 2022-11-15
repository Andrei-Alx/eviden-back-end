package nl.fontys.atosgame.cardservice.controller;

import java.util.UUID;
import java.util.function.Function;
import nl.fontys.atosgame.cardservice.event.BaseEvent;
import nl.fontys.atosgame.cardservice.event.CardDeletedEvent;
import nl.fontys.atosgame.cardservice.event.CardEvent;
import nl.fontys.atosgame.cardservice.model.Card;
import nl.fontys.atosgame.cardservice.service.CardEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

/**
 * Collection of all event producers for card events:
 * - CardCreated
 * - CardUpdated
 * - CardDeleted
 * @author Eli
 */
@Controller
public class CardEventProducers {

    private CardEventService cardEventService;

    public CardEventProducers(@Autowired CardEventService cardEventService) {
        this.cardEventService = cardEventService;
    }

    /**
     * Id: P-1
     * function to produce a CardCreated event
     * input topic: -
     * output topic: card-created-topic
     */
    @Bean
    public Function<Card, Message<CardEvent>> cardCreated() {
        return input -> {
            return cardEventService.cardCreated(input);
        };
    }

    /**
     * Id: P-2
     * function to produce a CardUpdated event
     * input topic: -
     * output topic: card-updated-topic
     */
    @Bean
    public Function<Card, Message<CardEvent>> cardUpdated() {
        return input -> {
            return cardEventService.cardUpdated(input);
        };
    }

    /**
     * Id: P-3
     * function to produce a CardDeleted event
     * input topic: -
     * output topic: card-deleted-topic
     */
    @Bean
    public Function<UUID, Message<CardDeletedEvent>> cardDeleted() {
        return input -> {
            return cardEventService.cardDeleted(input);
        };
    }
}
