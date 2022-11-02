package nl.fontys.atosgame.cardservice.service;

import nl.fontys.atosgame.cardservice.event.*;
import nl.fontys.atosgame.cardservice.model.CardSet;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CardSetEventServiceImpl implements CardSetEventService {
    /**
     * Create a CardSetCreated event
     * @param cardSet the card set that was created
     * @return card set created event with cardset id as key and event as value
     */
    @Override
    public Message<BaseEvent> cardSetCreated(CardSet cardSet) {
        CardSetEventData data = new CardSetEventData(cardSet);
        Object key = cardSet.getId();
        BaseEvent event = BaseEventFactory.create("CardSetCreated", "CardService", data);
        return MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, key)
                .build();
    }

    /**
     * Create a CardSetDeleted event
     * @param id the id of the card set that was deleted
     * @return card set deleted event with cardset id as key and event as value
     */
    @Override
    public Message<BaseEvent> cardSetDeleted(UUID id) {
        CardSetDeletedData data = new CardSetDeletedData(id);
        Object key = id;
        BaseEvent event = BaseEventFactory.create("CardSetDeleted", "CardService", data);
        return MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, key)
                .build();
    }

    /**
     * Create a CardSetUpdated event
     * @param cardSet the card set that was updated
     * @return card set updated event with cardset id as key and event as value
     */
    @Override
    public Message<BaseEvent> cardSetUpdated(CardSet cardSet) {
        CardSetEventData data = new CardSetEventData(cardSet);
        Object key = cardSet.getId();
        BaseEvent event = BaseEventFactory.create("CardSetUpdated", "CardService", data);
        return MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, key)
                .build();
    }
}
