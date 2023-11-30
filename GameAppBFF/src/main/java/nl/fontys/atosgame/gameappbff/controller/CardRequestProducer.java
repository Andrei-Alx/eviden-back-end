package nl.fontys.atosgame.gameappbff.controller;

import nl.fontys.atosgame.gameappbff.event.EventFactory;
import nl.fontys.atosgame.gameappbff.event.produced.CardSetRequestEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;

import java.util.UUID;
import java.util.function.Function;

@Controller
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
