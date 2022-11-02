package nl.fontys.atosgame.cardservice.service;

import nl.fontys.atosgame.cardservice.event.BaseEvent;
import nl.fontys.atosgame.cardservice.event.CardSetDeletedEvent;
import nl.fontys.atosgame.cardservice.event.CardSetEvent;
import nl.fontys.atosgame.cardservice.model.CardSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CardSetEventServiceImplTest {
    private CardSetEventServiceImpl cardSetEventService;

    @BeforeEach
    void setUp() {
        cardSetEventService = new CardSetEventServiceImpl();
    }

    @Test
    void cardSetCreated() {
        CardSet cardSet = mock(CardSet.class);

        Message<CardSetEvent> message = cardSetEventService.cardSetCreated(cardSet);

        assertEquals("CardSetCreated", message.getPayload().getType());
        assertEquals("CardService", message.getPayload().getService());
        assertInstanceOf(CardSetEvent.class, message.getPayload());
        assertEquals(cardSet, message.getPayload().getCardSet());
        assertEquals(cardSet.getId(), message.getHeaders().get("kafka_messageKey"));

    }

    @Test
    void cardSetDeleted() {
        UUID id = UUID.randomUUID();

        Message<CardSetDeletedEvent> message = cardSetEventService.cardSetDeleted(id);

        assertEquals("CardSetDeleted", message.getPayload().getType());
        assertEquals("CardService", message.getPayload().getService());
        assertInstanceOf(CardSetDeletedEvent.class, message.getPayload());
        assertEquals(id, message.getPayload().getId());
    }

    @Test
    void cardSetUpdated() {
          CardSet cardSet = mock(CardSet.class);

          Message<CardSetEvent> message = cardSetEventService.cardSetUpdated(cardSet);

          assertEquals("CardSetUpdated", message.getPayload().getType());
          assertEquals("CardService", message.getPayload().getService());
          assertInstanceOf(CardSetEvent.class, message.getPayload());
          assertEquals(cardSet, message.getPayload().getCardSet());
          assertEquals(cardSet.getId(), message.getHeaders().get("kafka_messageKey"));
    }
}