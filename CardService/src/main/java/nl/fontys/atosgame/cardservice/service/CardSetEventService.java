package nl.fontys.atosgame.cardservice.service;

import nl.fontys.atosgame.cardservice.event.BaseEvent;
import nl.fontys.atosgame.cardservice.model.CardSet;
import org.springframework.messaging.Message;

import java.util.UUID;

public interface CardSetEventService {
    Message<BaseEvent> cardSetCreated(CardSet cardSet);
    Message<BaseEvent> cardSetDeleted(UUID id);
    Message<BaseEvent> cardSetUpdated(CardSet cardSet);
}
