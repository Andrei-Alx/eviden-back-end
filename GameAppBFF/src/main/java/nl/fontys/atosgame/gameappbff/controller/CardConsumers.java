package nl.fontys.atosgame.gameappbff.controller;

import nl.fontys.atosgame.gameappbff.event.consumed.CardCreatedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.CardDeletedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.CardUpdatedEvent;
import nl.fontys.atosgame.gameappbff.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

/**
 * Collection of consumers for card related events:
 * - CardCreated
 * - CardUpdated
 * - CardDeleted
 * @author Aniek
 */
@Controller
public class CardConsumers {

    private CardService cardService;

    public CardConsumers(@Autowired CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * Id: C-42
     * Consumer for CardCreatedEvent
     * input topic: card-created-topic
     * output topic: -
     */
    @Bean
    public Function<Message<CardCreatedEvent>, Void> handleCardCreated() {
        return cardCreatedEventMessage -> {
            CardCreatedEvent event = cardCreatedEventMessage.getPayload();
            cardService.handleCardCreated(event.getCard());
            return null;
        };
    }

    /**
     * Id: C-43
     * Consumer for CardUpdatedEvent
     * input topic: card-updated-topic
     * output topic: -
     */
    @Bean
    public Function<Message<CardUpdatedEvent>, Void> handleCardUpdated() {
        return cardUpdatedEventMessage -> {
            CardUpdatedEvent event = cardUpdatedEventMessage.getPayload();
            cardService.handleCardUpdated(event.getCard());
            return null;
        };
    }

    /**
     * Id: C-44
     * Consumer for CardDeletedEvent
     * input topic: card-deleted-topic
     * output topic: -
     */
    @Bean
    public Function<Message<CardDeletedEvent>, Void> handleCardDeleted() {
        return cardDeletedEventMessage -> {
            CardDeletedEvent event = cardDeletedEventMessage.getPayload();
            cardService.handleCardDeleted(event.getCardId());
            return null;
        };
    }
}
