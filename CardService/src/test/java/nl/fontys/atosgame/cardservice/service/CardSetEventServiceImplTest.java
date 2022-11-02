package nl.fontys.atosgame.cardservice.service;

import nl.fontys.atosgame.cardservice.event.BaseEvent;
import nl.fontys.atosgame.cardservice.event.CardSetDeletedData;
import nl.fontys.atosgame.cardservice.event.CardSetEventData;
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

        Message<BaseEvent> message = cardSetEventService.cardSetCreated(cardSet);

        assertEquals("CardSetCreated", message.getPayload().getType());
        assertEquals("CardService", message.getPayload().getService());
        assertInstanceOf(CardSetEventData.class, message.getPayload().getData());
        assertEquals(cardSet, ((CardSetEventData) message.getPayload().getData()).getCardSet());
        assertEquals(cardSet.getId(), message.getHeaders().get("kafka_messageKey"));

    }

    @Test
    void cardSetDeleted() {
        UUID id = UUID.randomUUID();

        Message<BaseEvent> message = cardSetEventService.cardSetDeleted(id);

        assertEquals("CardSetDeleted", message.getPayload().getType());
        assertEquals("CardService", message.getPayload().getService());
        assertInstanceOf(CardSetDeletedData.class, message.getPayload().getData());
        assertEquals(id, ((CardSetDeletedData) message.getPayload().getData()).getCardSetId());
    }

    @Test
    void cardSetUpdated() {
          CardSet cardSet = mock(CardSet.class);

          Message<BaseEvent> message = cardSetEventService.cardSetUpdated(cardSet);

          assertEquals("CardSetUpdated", message.getPayload().getType());
          assertEquals("CardService", message.getPayload().getService());
          assertInstanceOf(CardSetEventData.class, message.getPayload().getData());
          assertEquals(cardSet, ((CardSetEventData) message.getPayload().getData()).getCardSet());
          assertEquals(cardSet.getId(), message.getHeaders().get("kafka_messageKey"));
    }
}