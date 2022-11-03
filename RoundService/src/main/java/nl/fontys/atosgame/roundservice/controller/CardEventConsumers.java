package nl.fontys.atosgame.roundservice.controller;

import nl.fontys.atosgame.cardservice.event.BaseEvent;
import nl.fontys.atosgame.cardservice.event.CardDeletedEvent;
import nl.fontys.atosgame.cardservice.event.CardEvent;
import nl.fontys.atosgame.roundservice.model.Card;
import nl.fontys.atosgame.roundservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

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

    /**
     * function to consume a CardCreated event
     * input topic: card-created-topic
     * output topic: -
     */
    @Bean
    public Function<Message<CardEvent>, Void> handleCardCreated() {
        return message -> {
            CardEvent event = message.getPayload();
            // Convert to round service card
            Card card = new Card(event.getCard().getId(), event.getCard().getTags());
            cardService.createCard(card);
            return null;
        };
    }

    /**
     * function to consume a CardUpdated event
     * input topic: card-updated-topic
     * output topic: -
     */
    @Bean
    public Function<Message<CardEvent>, Void> handleCardUpdated() {
        return message -> {
            CardEvent event = message.getPayload();
            // Convert to round service card
            Card card = new Card(event.getCard().getId(), event.getCard().getTags());
            cardService.updateCard(card);
            return null;
        };
    }

    /**
     * function to consume a CardDeleted event
     * input topic: card-deleted-topic
     * output topic: -
     */
    @Bean
    public Function<Message<CardDeletedEvent>, Void> handleCardDeleted() {
        return message -> {
            CardDeletedEvent event = message.getPayload();
            cardService.deleteCard(event.getCardId());
            return null;
        };
    }
}
