package nl.fontys.atosgame.cardservice.controller;

import nl.fontys.atosgame.cardservice.event.BaseEvent;
import nl.fontys.atosgame.cardservice.model.CardSet;
import nl.fontys.atosgame.cardservice.service.CardSetEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

import java.util.UUID;
import java.util.function.Function;

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
     * function to produce a CardSetCreated event
     * input topic: -
     * output topic: card-set-created-topic
     */
    @Bean
    public Function<CardSet, Message<BaseEvent>> cardSetCreated() {
        return (input) -> {
            return cardSetEventService.cardSetCreated(input);
        };
    }

    /**
     * function to produce a CardSetUpdated event
     * input topic: -
     * output topic: card-set-updated-topic
     */
    @Bean
    public Function<CardSet, Message<BaseEvent>> cardSetUpdated() {
        return (input) -> {
            return cardSetEventService.cardSetUpdated(input);
        };
    }

    /**
     * function to produce a CardSetDeleted event
     * input topic: -
     * output topic: card-set-deleted-topic
     */
    @Bean
    public Function<UUID, Message<BaseEvent>> cardSetDeleted() {
        return (input) -> {
            return cardSetEventService.cardSetDeleted(input);
        };
    }
}
