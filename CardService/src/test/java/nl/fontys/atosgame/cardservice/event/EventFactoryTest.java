/*
package nl.fontys.atosgame.cardservice.event;

import nl.fontys.atosgame.cardservice.event.produced.CardSetEvent;
import nl.fontys.atosgame.cardservice.model.Card;
import nl.fontys.atosgame.cardservice.model.CardSet;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class EventFactoryTest {

    @Test
    void createCardCreatedEvent() {
        Card card = mock(Card.class);

        CardEvent cardCreatedEvent = EventFactory.createCardCreatedEvent("Test", card);

        assertNotNull(cardCreatedEvent);
        assertNotNull(cardCreatedEvent.getId());
        assertNotNull(cardCreatedEvent.getTimestamp());
        assertEquals("CardCreated", cardCreatedEvent.getType());
        assertEquals("Test", cardCreatedEvent.getService());
        assertEquals(card, cardCreatedEvent.getCard());
    }

    @Test
    void createCardUpdatedEvent() {
        Card card = mock(Card.class);

        CardEvent cardUpdatedEvent = EventFactory.createCardUpdatedEvent("Test", card);

        assertNotNull(cardUpdatedEvent);
        assertNotNull(cardUpdatedEvent.getId());
        assertNotNull(cardUpdatedEvent.getTimestamp());
        assertEquals("CardUpdated", cardUpdatedEvent.getType());
        assertEquals("Test", cardUpdatedEvent.getService());
        assertEquals(card, cardUpdatedEvent.getCard());
    }

    @Test
    void createCardDeletedEvent() {
        UUID id = UUID.randomUUID();

        CardDeletedEvent cardDeletedEvent = EventFactory.createCardDeletedEvent(
            "Test",
            id
        );

        assertNotNull(cardDeletedEvent);
        assertNotNull(cardDeletedEvent.getId());
        assertNotNull(cardDeletedEvent.getTimestamp());
        assertEquals("CardDeleted", cardDeletedEvent.getType());
        assertEquals("Test", cardDeletedEvent.getService());
        assertEquals(id, cardDeletedEvent.getCardId());
    }

    @Test
    void createCardSetCreatedEvent() {
        CardSet cardSet = mock(CardSet.class);

        CardSetEvent cardSetCreatedEvent = EventFactory.createCardSetCreatedEvent(
            "Test",
            cardSet
        );

        assertNotNull(cardSetCreatedEvent);
        assertNotNull(cardSetCreatedEvent.getId());
        assertNotNull(cardSetCreatedEvent.getTimestamp());
        assertEquals("CardSetCreated", cardSetCreatedEvent.getType());
        assertEquals("Test", cardSetCreatedEvent.getService());
        assertEquals(cardSet, cardSetCreatedEvent.getCardSet());
    }

    @Test
    void createCardSetUpdatedEvent() {
        CardSet cardSet = mock(CardSet.class);

        CardSetEvent cardSetUpdatedEvent = EventFactory.createCardSetUpdatedEvent(
            "Test",
            cardSet
        );

        assertNotNull(cardSetUpdatedEvent);
        assertNotNull(cardSetUpdatedEvent.getId());
        assertNotNull(cardSetUpdatedEvent.getTimestamp());
        assertEquals("CardSetUpdated", cardSetUpdatedEvent.getType());
        assertEquals("Test", cardSetUpdatedEvent.getService());
        assertEquals(cardSet, cardSetUpdatedEvent.getCardSet());
    }

    @Test
    void createCardSetDeletedEvent() {
        UUID id = UUID.randomUUID();

        CardSetDeletedEvent cardSetDeletedEvent = EventFactory.createCardSetDeletedEvent(
            "Test",
            id
        );

        assertNotNull(cardSetDeletedEvent);
        assertNotNull(cardSetDeletedEvent.getId());
        assertNotNull(cardSetDeletedEvent.getTimestamp());
        assertEquals("CardSetDeleted", cardSetDeletedEvent.getType());
        assertEquals("Test", cardSetDeletedEvent.getService());
        assertEquals(id, cardSetDeletedEvent.getCardSetId());
    }
}
*/
