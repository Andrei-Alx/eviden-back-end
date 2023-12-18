package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.event.produced.CardSetRequestEvent;
import org.springframework.messaging.Message;

public interface CardSetEventService {
    Message<CardSetRequestEvent> cardSetRequest();
}
