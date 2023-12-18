package nl.fontys.atosgame.gameservice.controllers;

import nl.fontys.atosgame.gameservice.event.EventFactory;
import nl.fontys.atosgame.gameservice.event.produced.CardSetRequestEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;
import java.util.function.Function;

public class CardRequestProducer {

    @Bean
    public Function<Integer, Message<CardSetRequestEvent>> cardSetRequest() {
        return input -> {
            CardSetRequestEvent event = EventFactory.cardsRequestEvent();
            Object key = UUID.randomUUID();
            Message<CardSetRequestEvent> message = MessageBuilder
                    .withPayload(event)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, key)
                    .build();
            return message;
        };
    }
}
