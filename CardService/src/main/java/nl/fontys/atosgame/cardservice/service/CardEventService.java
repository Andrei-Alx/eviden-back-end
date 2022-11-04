package nl.fontys.atosgame.cardservice.service;

import nl.fontys.atosgame.cardservice.event.BaseEvent;
import nl.fontys.atosgame.cardservice.event.CardDeletedEvent;
import nl.fontys.atosgame.cardservice.event.CardEvent;
import nl.fontys.atosgame.cardservice.model.Card;
import org.springframework.messaging.Message;

import java.util.UUID;

public interface CardEventService {
    Message<CardEvent> cardCreated(Card card);
    Message<CardEvent> cardUpdated(Card card);
    Message<CardDeletedEvent> cardDeleted(UUID id);
}
