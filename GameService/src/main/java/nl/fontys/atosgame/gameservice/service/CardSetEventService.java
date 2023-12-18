package nl.fontys.atosgame.gameservice.service;

import nl.fontys.atosgame.gameservice.event.produced.CardSetRequestEvent;
import org.springframework.messaging.Message;

public interface CardSetEventService {
    Message<CardSetRequestEvent> cardSetRequest();
}
