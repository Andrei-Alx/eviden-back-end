package nl.fontys.atosgame.cardservice.service;

import nl.fontys.atosgame.cardservice.event.BaseEvent;
import nl.fontys.atosgame.cardservice.event.BaseEventFactory;
import nl.fontys.atosgame.cardservice.event.CardDeletedData;
import nl.fontys.atosgame.cardservice.event.CardEventData;
import nl.fontys.atosgame.cardservice.model.Card;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    public Message<BaseEvent> cardCreated(Card card) {
        CardEventData data = new CardEventData(card);
        Object key = card.getId();
        BaseEvent event = BaseEventFactory.create("CardCreated", "CardService", data);
        return MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, key)
                .build();
    }

    /**
     * Create a CardUpdated event
     * @param card the card that was updated
     * @return card updated event with key and value
     */
    @Override
    public Message<BaseEvent> cardUpdated(Card card) {
        CardEventData data = new CardEventData(card);
        Object key = card.getId();
        BaseEvent event = BaseEventFactory.create("CardUpdated", "CardService", data);
        return MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, key)
                .build();
    }

    /**
     * Create a CardDeleted event
     * @param id the id of the card that was deleted
     * @return card deleted event with key and value
     */
    @Override
    public Message<BaseEvent> cardDeleted(UUID id) {
        CardDeletedData data = new CardDeletedData(id);
        Object key = id;
        BaseEvent event = BaseEventFactory.create("CardDeleted", "CardService", data);
        return MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, key)
                .build();
    }
}
