package nl.fontys.atosgame.cardservice.event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class BaseEventFactoryTest {

    @Test
    void create() {
        CardEventData data = mock(CardEventData.class);
        BaseEvent event = BaseEventFactory.create("CardCreated", "CardService", data);
        assertNotNull(event);
        assertNotNull(event.getId());
        assertNotNull(event.getTimestamp());
        assertEquals("CardCreated", event.getType());
        assertEquals("CardService", event.getService());
        assertEquals(data, event.getData());
    }
}