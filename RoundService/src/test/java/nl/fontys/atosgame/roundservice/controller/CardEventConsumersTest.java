package nl.fontys.atosgame.roundservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.event.CardDeletedEvent;
import nl.fontys.atosgame.roundservice.event.CardEvent;
import nl.fontys.atosgame.roundservice.model.Card;
import nl.fontys.atosgame.roundservice.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;

class CardEventConsumersTest {

    private CardService cardService;
    private CardEventConsumers cardEventConsumers;

    @BeforeEach
    void setUp() {
        cardService = mock(CardService.class);
        cardEventConsumers = new CardEventConsumers(cardService);
    }

    @Test
    void handleCardCreated() {
        Message<CardEvent> message = mock(Message.class);
        CardEvent event = mock(CardEvent.class);
        Card card = mock(Card.class);
        doReturn(UUID.randomUUID()).when(card).getId();
        doReturn(List.of("tag1", "tag2")).when(card).getTags();
        doReturn(card).when(event).getCard();
        doReturn(event).when(message).getPayload();

        cardEventConsumers.handleCardCreated().apply(message);

        verify(cardService).createCard(card);
    }

    @Test
    void handleCardUpdated() {
        Message<CardEvent> message = mock(Message.class);
        CardEvent event = mock(CardEvent.class);
        Card card = mock(Card.class);
        doReturn(UUID.randomUUID()).when(card).getId();
        doReturn(List.of("tag1", "tag2")).when(card).getTags();
        doReturn(card).when(event).getCard();
        doReturn(event).when(message).getPayload();

        cardEventConsumers.handleCardUpdated().apply(message);

        verify(cardService).updateCard(card);
    }

    @Test
    void handleCardDeleted() {
        Message<CardDeletedEvent> message = mock(Message.class);
        CardDeletedEvent event = mock(CardDeletedEvent.class);
        doReturn(UUID.randomUUID()).when(event).getId();
        doReturn(event).when(message).getPayload();

        cardEventConsumers.handleCardDeleted().apply(message);

        verify(cardService).deleteCard(message.getPayload().getCardId());
    }
}
