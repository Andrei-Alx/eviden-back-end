package nl.fontys.atosgame.cardservice.service.interfaces;

import nl.fontys.atosgame.cardservice.event.produced.CardSetEvent;
import nl.fontys.atosgame.cardservice.seeder.CardSeeder;
import org.springframework.messaging.Message;

public interface CardSetEventService {
    Message<CardSetEvent> produceCardSet(CardSetEvent event);
}
