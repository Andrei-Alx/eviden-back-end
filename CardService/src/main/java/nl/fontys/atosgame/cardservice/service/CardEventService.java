package nl.fontys.atosgame.cardservice.service;

import nl.fontys.atosgame.cardservice.event.BaseEvent;
import nl.fontys.atosgame.cardservice.model.Card;
import org.springframework.messaging.Message;

import java.util.UUID;

public interface CardEventService {
    Message<BaseEvent> cardCreated(Card card);
    Message<BaseEvent> cardUpdated(Card card);
    Message<BaseEvent> cardDeleted(UUID id);
}
