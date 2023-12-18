package nl.fontys.atosgame.gameservice.service;

import nl.fontys.atosgame.gameservice.event.EventFactory;
import nl.fontys.atosgame.gameservice.event.produced.CardSetRequestEvent;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CardSetEventServiceImpl implements CardSetEventService{

    @Override
    public Message<CardSetRequestEvent> cardSetRequest() {
        CardSetRequestEvent event = EventFactory.cardsRequestEvent();
        Object key = UUID.randomUUID();
        return MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, key)
                .build();
    }
}
