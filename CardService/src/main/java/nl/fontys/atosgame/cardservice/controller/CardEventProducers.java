package nl.fontys.atosgame.cardservice.controller;

import nl.fontys.atosgame.cardservice.event.BaseEvent;
import nl.fontys.atosgame.cardservice.event.BaseEventFactory;
import nl.fontys.atosgame.cardservice.event.CardDeletedData;
import nl.fontys.atosgame.cardservice.event.CardEventData;
import nl.fontys.atosgame.cardservice.model.Card;
import nl.fontys.atosgame.cardservice.service.CardEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

import java.util.UUID;
import java.util.function.Function;

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
     * function to produce a CardCreated event
     * input topic: -
     * output topic: card-created-topic
     */
    @Bean
    public Function<Card, Message<BaseEvent>> cardCreated() {
        return (input) -> {
            return cardEventService.cardCreated(input);
        };
    }

    /**
     * function to produce a CardUpdated event
     * input topic: -
     * output topic: card-updated-topic
     */
    @Bean
    public Function<Card, Message<BaseEvent>> cardUpdated() {
        return (input) -> {
            return cardEventService.cardUpdated(input);
        };
    }

    /**
     * function to produce a CardDeleted event
     * input topic: -
     * output topic: card-deleted-topic
     */
    @Bean
    public Function<UUID, Message<BaseEvent>> cardDeleted() {
        return (input) -> {
            return cardEventService.cardDeleted(input);
        };
    }
}
