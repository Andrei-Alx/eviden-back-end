package nl.fontys.atosgame.roundservice.controller;

import nl.fontys.atosgame.roundservice.event.CardDeletedEvent;
import nl.fontys.atosgame.roundservice.event.CardEvent;
import nl.fontys.atosgame.roundservice.model.Card;
import nl.fontys.atosgame.roundservice.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

        verify(cardService).createCard(new nl.fontys.atosgame.roundservice.model.Card(message.getPayload().getCard().getId(), message.getPayload().getCard().getTags()));
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

        verify(cardService).updateCard(new nl.fontys.atosgame.roundservice.model.Card(message.getPayload().getCard().getId(), message.getPayload().getCard().getTags()));
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