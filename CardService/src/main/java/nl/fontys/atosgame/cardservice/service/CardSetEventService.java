package nl.fontys.atosgame.cardservice.service;

import nl.fontys.atosgame.cardservice.event.BaseEvent;
import nl.fontys.atosgame.cardservice.event.CardSetDeletedEvent;
import nl.fontys.atosgame.cardservice.event.CardSetEvent;
import nl.fontys.atosgame.cardservice.model.CardSet;
import org.springframework.messaging.Message;

import java.util.UUID;

public interface CardSetEventService {
    Message<CardSetEvent> cardSetCreated(CardSet cardSet);
    Message<CardSetDeletedEvent> cardSetDeleted(UUID id);
    Message<CardSetEvent> cardSetUpdated(CardSet cardSet);
}
