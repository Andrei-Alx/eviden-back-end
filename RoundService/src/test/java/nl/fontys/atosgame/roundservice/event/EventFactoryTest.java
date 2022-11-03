package nl.fontys.atosgame.roundservice.event;

import nl.fontys.atosgame.roundservice.model.Round;
import nl.fontys.atosgame.roundservice.model.RoundSettings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EventFactoryTest {

    @Test
    void createRoundCreatedEvent() {
        Round round = new Round(UUID.randomUUID(), new ArrayList<>(), "Status", new RoundSettings());

        RoundCreatedEvent event = EventFactory.createRoundCreatedEvent("RoundService", round);

        assertEquals("RoundCreated", event.getType());
        assertEquals("RoundService", event.getService());
        assertEquals(round, event.getRound());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getId());
    }
}