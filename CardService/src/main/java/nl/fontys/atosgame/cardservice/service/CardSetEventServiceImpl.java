
package nl.fontys.atosgame.cardservice.service;

import java.util.UUID;
import nl.fontys.atosgame.cardservice.event.*;
import nl.fontys.atosgame.cardservice.event.produced.CardSetEvent;
import nl.fontys.atosgame.cardservice.service.interfaces.CardSetEventService;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * Service to create events for card set events
 * @author Eli
 */

@Service
public class CardSetEventServiceImpl implements CardSetEventService {

    @Override
    public Message<CardSetEvent> produceCardSet(CardSetEvent data) {
        CardSetEvent event = EventFactory.createCardSetEvent(
                "CardService",
                data.getCardSets()
        );
        Object key = UUID.randomUUID();
        return MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.KEY, key)
                .build();
    }
}

