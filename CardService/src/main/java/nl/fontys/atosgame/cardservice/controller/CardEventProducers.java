package nl.fontys.atosgame.cardservice.controller;

import nl.fontys.atosgame.cardservice.event.BaseEvent;
import nl.fontys.atosgame.cardservice.event.BaseEventFactory;
import nl.fontys.atosgame.cardservice.event.CardDeletedData;
import nl.fontys.atosgame.cardservice.event.CardEventData;
import nl.fontys.atosgame.cardservice.model.Card;
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
 */
@Controller
public class CardEventProducers {

    /**
     * function to produce a CardCreated event
     * input topic: -
     * output topic: card-created-topic
     */
    @Bean
    public Function<Card, Message<BaseEvent>> cardCreated() {
        return (input) -> {
            CardEventData data = new CardEventData(input);
            Object key = input.getId();
            BaseEvent event = BaseEventFactory.create("CardCreated", "CardService", data);
            Message<BaseEvent> message = MessageBuilder.withPayload(event)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, key)
                    .build();
            return message;
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
            CardEventData data = new CardEventData(input);
            Object key = input.getId();
            BaseEvent event = BaseEventFactory.create("CardUpdated", "CardService", data);
            Message<BaseEvent> message = MessageBuilder.withPayload(event)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, key)
                    .build();
            return message;
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
            CardDeletedData data = new CardDeletedData(input);
            BaseEvent event = BaseEventFactory.create("CardDeleted", "CardService", data);
            Message<BaseEvent> message = MessageBuilder.withPayload(event)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, input)
                    .build();
            return message;
        };
    }
}
