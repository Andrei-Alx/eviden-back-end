package nl.fontys.atosgame.cardservice.service;

import nl.fontys.atosgame.cardservice.event.*;
import nl.fontys.atosgame.cardservice.model.CardSet;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service to create events for card set events
 * @author Eli
 */
@Service
public class CardSetEventServiceImpl implements CardSetEventService {
    /**
     * Create a CardSetCreated event
     *
     * @param cardSet the card set that was created
     * @return card set created event with cardset id as key and event as value
     */
    @Override
    public Message<CardSetEvent> cardSetCreated(CardSet cardSet) {
        CardSetEvent event = EventFactory.createCardSetCreatedEvent("CardService", cardSet);
        Object key = cardSet.getId();
        return MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, key)
                .build();
    }

    /**
     * Create a CardSetDeleted event
     *
     * @param id the id of the card set that was deleted
     * @return card set deleted event with cardset id as key and event as value
     */
    @Override
    public Message<CardSetDeletedEvent> cardSetDeleted(UUID id) {
        CardSetDeletedEvent event = EventFactory.createCardSetDeletedEvent("CardService", id);
        Object key = id;
        return MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, key)
                .build();
    }

    /**
     * Create a CardSetUpdated event
     *
     * @param cardSet the card set that was updated
     * @return card set updated event with cardset id as key and event as value
     */
    @Override
    public Message<CardSetEvent> cardSetUpdated(CardSet cardSet) {
        CardSetEvent event = EventFactory.createCardSetUpdatedEvent("CardService", cardSet);
        Object key = cardSet.getId();
        return MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, key)
                .build();
    }
}
