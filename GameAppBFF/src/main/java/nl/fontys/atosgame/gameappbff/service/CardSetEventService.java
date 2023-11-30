package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.event.produced.CardSetRequestEvent;
import org.springframework.messaging.Message;

public interface CardSetEventService {

    Message<CardSetRequestEvent> cardSetRequest();
}
