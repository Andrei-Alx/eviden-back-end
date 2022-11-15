package nl.fontys.atosgame.cardservice.service;

import java.util.UUID;
import nl.fontys.atosgame.cardservice.event.CardDeletedEvent;
import nl.fontys.atosgame.cardservice.event.CardEvent;
import nl.fontys.atosgame.cardservice.event.EventFactory;
import nl.fontys.atosgame.cardservice.model.Card;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * Service to create card events
 * @author Eli
 */
@Service
public class CardEventServiceImpl implements CardEventService {

    /**
     * Create a CardCreated event
     * @param card the card that was created
     * @return card created event with key and value
     */
    @Override
    public Message<CardEvent> cardCreated(Card card) {
        CardEvent event = EventFactory.createCardCreatedEvent("CardService", card);
        Object key = card.getId();
        return MessageBuilder
            .withPayload(event)
            .setHeader(KafkaHeaders.MESSAGE_KEY, key)
            .build();
    }

    /**
     * Create a CardUpdated event
     * @param card the card that was updated
     * @return card updated event with key and value
     */
    @Override
    public Message<CardEvent> cardUpdated(Card card) {
        CardEvent event = EventFactory.createCardUpdatedEvent("CardService", card);
        Object key = card.getId();
        return MessageBuilder
            .withPayload(event)
            .setHeader(KafkaHeaders.MESSAGE_KEY, key)
            .build();
    }

    /**
     * Create a CardDeleted event
     * @param id the id of the card that was deleted
     * @return card deleted event with key and value
     */
    @Override
    public Message<CardDeletedEvent> cardDeleted(UUID id) {
        CardDeletedEvent event = EventFactory.createCardDeletedEvent("CardService", id);
        Object key = id;
        return MessageBuilder
            .withPayload(event)
            .setHeader(KafkaHeaders.MESSAGE_KEY, key)
            .build();
    }
}
