package nl.fontys.atosgame.roundservice.controller;

import java.util.function.Function;
import nl.fontys.atosgame.roundservice.event.CardSetDeletedEvent;
import nl.fontys.atosgame.roundservice.event.CardSetEvent;
import nl.fontys.atosgame.roundservice.service.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

@Controller
public class CardSetEventConsumers {

    private CardSetService cardSetService;

    public CardSetEventConsumers(@Autowired CardSetService cardSetService) {
        this.cardSetService = cardSetService;
    }

    //    private CardSetConverter cardSetConverter;

    //    public CardSetEventConsumers(@Autowired CardSetService cardSetService, @Autowired CardSetConverter cardSetConverter) {
    //        this.cardSetService = cardSetService;
    //        this.cardSetConverter = cardSetConverter;
    //    }

    /**
     * Id: C-23
     * function to consume a CardSetCreated event
     * input topic: card-set-created-topic
     * output topic: -
     */
    @Bean
    public Function<Message<CardSetEvent>, Void> handleCardSetCreated() {
        return message -> {
            CardSetEvent event = message.getPayload();
            cardSetService.createCardSet(event.getCardSet());
            return null;
        };
    }

    /**
     * Id: C-24
     * function to consume a CardSetUpdated event
     * input topic: card-set-updated-topic
     * output topic: -
     */
    @Bean
    public Function<Message<CardSetEvent>, Void> handleCardSetUpdated() {
        return message -> {
            CardSetEvent event = message.getPayload();
            // Convert to round service cardset
            cardSetService.updateCardSet(event.getCardSet());
            return null;
        };
    }

    /**
     * Id: C-25
     * function to consume a CardSetDeleted event
     * input topic: card-set-deleted-topic
     * output topic: -
     */
    @Bean
    public Function<Message<CardSetDeletedEvent>, Void> handleCardSetDeleted() {
        return message -> {
            CardSetDeletedEvent event = message.getPayload();
            cardSetService.deleteCardSet(event.getCardSetId());
            return null;
        };
    }
}
