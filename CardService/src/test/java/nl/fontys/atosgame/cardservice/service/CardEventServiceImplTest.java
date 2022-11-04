package nl.fontys.atosgame.cardservice.service;

import nl.fontys.atosgame.cardservice.event.BaseEvent;
import nl.fontys.atosgame.cardservice.event.CardDeletedEvent;
import nl.fontys.atosgame.cardservice.event.CardEvent;
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

        Message<CardEvent> message = cardService.cardCreated(card);

        assertEquals("CardCreated", message.getPayload().getType());
        assertEquals("CardService", message.getPayload().getService());
        assertInstanceOf(CardEvent.class, message.getPayload());
        assertEquals(card, message.getPayload().getCard());
        assertEquals(card.getId(), message.getHeaders().get("kafka_messageKey"));
    }

    @Test
    void cardUpdated() {
        Card card = mock(Card.class);

        Message<CardEvent> message = cardService.cardUpdated(card);

        assertEquals("CardUpdated", message.getPayload().getType());
        assertEquals("CardService", message.getPayload().getService());
        assertInstanceOf(CardEvent.class, message.getPayload());
        assertEquals(card, message.getPayload().getCard());
        assertEquals(card.getId(), message.getHeaders().get("kafka_messageKey"));
    }

    @Test
    void cardDeleted() {
        UUID id = UUID.randomUUID();

        Message<CardDeletedEvent> message = cardService.cardDeleted(id);

        assertEquals("CardDeleted", message.getPayload().getType());
        assertEquals("CardService", message.getPayload().getService());
        assertInstanceOf(CardDeletedEvent.class, message.getPayload());
        assertEquals(id, message.getPayload().getCardId());
        assertEquals(id, message.getHeaders().get("kafka_messageKey"));
    }
}