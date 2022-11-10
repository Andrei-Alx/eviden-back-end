package nl.fontys.atosgame.adminappbff.controller;

import nl.fontys.atosgame.gameappbff.event.consumed.CardCreatedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.CardDeletedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.CardUpdatedEvent;
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
            //TODO
            throw new UnsupportedOperationException("Not implemented yet");
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
            //TODO
            throw new UnsupportedOperationException("Not implemented yet");
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
            //TODO
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }
}
