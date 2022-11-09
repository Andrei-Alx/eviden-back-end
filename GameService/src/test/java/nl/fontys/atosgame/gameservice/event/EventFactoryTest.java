package nl.fontys.atosgame.gameservice.event;

import nl.fontys.atosgame.gameservice.event.consumed.RoundStartedEvent;
import nl.fontys.atosgame.gameservice.event.produced.GameCreatedEvent;
import nl.fontys.atosgame.gameservice.model.LobbySettings;
import nl.fontys.atosgame.gameservice.model.RoundSettings;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EventFactoryTest {
    @Test
    void createGameCreatedEvent() {
        UUID gameId = UUID.randomUUID();
        List<RoundSettings> roundSettings = new ArrayList<>();
        LobbySettings lobbySettings = new LobbySettings();

        GameCreatedEvent event = EventFactory.createGameCreatedEvent(gameId, roundSettings, lobbySettings);

        assertEquals("GameCreated", event.getType());
        assertEquals("GameService", event.getService());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getId());
        assertEquals(gameId, event.getGameId());
        assertEquals(roundSettings, event.getRoundSettings());
        assertEquals(lobbySettings, event.getLobbySettings());
    }
}