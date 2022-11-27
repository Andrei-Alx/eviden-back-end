package nl.fontys.atosgame.gameappbff.controller;

import java.util.function.Function;
import nl.fontys.atosgame.gameappbff.event.consumed.CardSetCreatedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.CardSetDeletedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.CardSetUpdatedEvent;
import nl.fontys.atosgame.gameappbff.service.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private CardSetService cardSetService;

    public CardSetConsumers(@Autowired CardSetService cardSetService) {
        this.cardSetService = cardSetService;
    }

    /**
     * Id: C-45
     * Consumer for CardSetCreatedEvent
     * input topic: card-set-created-topic
     * output topic: -
     */
    @Bean
    public Function<Message<CardSetCreatedEvent>, Void> handleCardSetCreated() {
        return cardSetCreatedEventMessage -> {
            CardSetCreatedEvent event = cardSetCreatedEventMessage.getPayload();
            cardSetService.handleCardSetCreated(event.getCardSet());
            return null;
        };
    }

    /**
     * Id: C-46
     * Consumer for CardSetUpdatedEvent
     * input topic: card-set-updated-topic
     * output topic: -
     */
    @Bean
    public Function<Message<CardSetUpdatedEvent>, Void> handleCardSetUpdated() {
        return cardSetUpdatedEventMessage -> {
            CardSetUpdatedEvent event = cardSetUpdatedEventMessage.getPayload();
            cardSetService.handleCardSetUpdated(event.getCardSet());
            return null;
        };
    }

    /**
     * Id: C-47
     * Consumer for CardSetDeletedEvent
     * input topic: card-set-deleted-topic
     * output topic: -
     */
    @Bean
    public Function<Message<CardSetDeletedEvent>, Void> handleCardSetDeleted() {
        return cardSetDeletedEventMessage -> {
            CardSetDeletedEvent event = cardSetDeletedEventMessage.getPayload();
            cardSetService.handleCardSetDeleted(event.getId());
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }
}
