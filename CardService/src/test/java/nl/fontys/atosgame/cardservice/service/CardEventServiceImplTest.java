package nl.fontys.atosgame.cardservice.service;

import nl.fontys.atosgame.cardservice.event.BaseEvent;
import nl.fontys.atosgame.cardservice.event.CardDeletedData;
import nl.fontys.atosgame.cardservice.event.CardEventData;
import nl.fontys.atosgame.cardservice.model.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CardEventServiceImplTest {

    CardEventServiceImpl cardService;

    @BeforeEach
    void setUp() {
        cardService = new CardEventServiceImpl();
    }

    @Test
    void cardCreated() {
        Card card = mock(Card.class);

        Message<BaseEvent> message = cardService.cardCreated(card);

        assertEquals("CardCreated", message.getPayload().getType());
        assertEquals("CardService", message.getPayload().getService());
        assertInstanceOf(CardEventData.class, message.getPayload().getData());
        assertEquals(card, ((CardEventData) message.getPayload().getData()).getCard());
        assertEquals(card.getId(), message.getHeaders().get("kafka_messageKey"));
    }

    @Test
    void cardUpdated() {
        Card card = mock(Card.class);

        Message<BaseEvent> message = cardService.cardUpdated(card);

        assertEquals("CardUpdated", message.getPayload().getType());
        assertEquals("CardService", message.getPayload().getService());
        assertInstanceOf(CardEventData.class, message.getPayload().getData());
        assertEquals(card, ((CardEventData) message.getPayload().getData()).getCard());
        assertEquals(card.getId(), message.getHeaders().get("kafka_messageKey"));
    }

    @Test
    void cardDeleted() {
        UUID id = UUID.randomUUID();

        Message<BaseEvent> message = cardService.cardDeleted(id);

        assertEquals("CardDeleted", message.getPayload().getType());
        assertEquals("CardService", message.getPayload().getService());
        assertInstanceOf(CardDeletedData.class, message.getPayload().getData());
        assertEquals(id, ((CardDeletedData) message.getPayload().getData()).getId());
        assertEquals(id, message.getHeaders().get("kafka_messageKey"));
    }
}