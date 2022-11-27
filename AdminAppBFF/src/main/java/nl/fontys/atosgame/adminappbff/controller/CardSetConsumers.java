package nl.fontys.atosgame.adminappbff.controller;

import java.util.function.Function;
import nl.fontys.atosgame.adminappbff.event.consumed.CardSetCreatedEvent;
import nl.fontys.atosgame.adminappbff.event.consumed.CardSetDeletedEvent;
import nl.fontys.atosgame.adminappbff.event.consumed.CardSetUpdatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

/**
 * Collection of consumers for cardset related events:
 * - CardSetCreated
 * - CardSetUpdated
 * - CardSetDeleted
 * @author Aniek
 */
@Controller
public class CardSetConsumers {

    /**
     * Id: C-51
     * Consumer for CardSetCreatedEvent
     * input topic: card-set-created-topic
     * output topic: -
     */
    @Bean
    public Function<Message<CardSetCreatedEvent>, Void> handleCardSetCreated() {
        return cardSetCreatedEventMessage -> {
            CardSetCreatedEvent event = cardSetCreatedEventMessage.getPayload();
            //TODO
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * Id: C-52
     * Consumer for CardSetUpdatedEvent
     * input topic: card-set-updated-topic
     * output topic: -
     */
    @Bean
    public Function<Message<CardSetUpdatedEvent>, Void> handleCardSetUpdated() {
        return cardSetUpdatedEventMessage -> {
            CardSetUpdatedEvent event = cardSetUpdatedEventMessage.getPayload();
            //TODO
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * Id: C-53
     * Consumer for CardSetDeletedEvent
     * input topic: card-set-deleted-topic
     * output topic: -
     */
    @Bean
    public Function<Message<CardSetDeletedEvent>, Void> handleCardSetDeleted() {
        return cardSetDeletedEventMessage -> {
            CardSetDeletedEvent event = cardSetDeletedEventMessage.getPayload();
            //TODO
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }
}
