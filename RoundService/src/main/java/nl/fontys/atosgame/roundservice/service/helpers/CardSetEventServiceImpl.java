package nl.fontys.atosgame.roundservice.service.helpers;

import nl.fontys.atosgame.roundservice.event.EventFactory;
import nl.fontys.atosgame.roundservice.event.produced.CardSetRequestEvent;
import nl.fontys.atosgame.roundservice.service.interfaces.CardSetEventService;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CardSetEventServiceImpl implements CardSetEventService {

    @Override
    public Message<CardSetRequestEvent> cardSetRequest() {
        CardSetRequestEvent event = EventFactory.cardsRequestEvent();
        Object key = UUID.randomUUID();
        return MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.KEY, key)
                .build();
    }
}
