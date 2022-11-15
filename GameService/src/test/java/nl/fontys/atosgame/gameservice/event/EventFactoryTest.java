package nl.fontys.atosgame.gameservice.event;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import nl.fontys.atosgame.gameservice.event.consumed.RoundStartedEvent;
import nl.fontys.atosgame.gameservice.event.produced.GameCreatedEvent;
import nl.fontys.atosgame.gameservice.event.produced.GameEndedEvent;
import nl.fontys.atosgame.gameservice.event.produced.GameStartedEvent;
import nl.fontys.atosgame.gameservice.model.LobbySettings;
import nl.fontys.atosgame.gameservice.model.RoundSettings;
import org.junit.jupiter.api.Test;

class EventFactoryTest {

    @Test
    void createGameCreatedEvent() {
        UUID gameId = UUID.randomUUID();
        List<RoundSettings> roundSettings = new ArrayList<>();
        LobbySettings lobbySettings = new LobbySettings();

        GameCreatedEvent event = EventFactory.createGameCreatedEvent(
            gameId,
            roundSettings,
            lobbySettings
        );

        assertEquals("GameCreated", event.getType());
        assertEquals("GameService", event.getService());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getId());
        assertEquals(gameId, event.getGameId());
        assertEquals(roundSettings, event.getRoundSettings());
        assertEquals(lobbySettings, event.getLobbySettings());
    }

    @Test
    void createGameStartedEvent() {
        UUID gameId = UUID.randomUUID();

        GameStartedEvent event = EventFactory.createGameStartedEvent(gameId);

        assertEquals("GameStarted", event.getType());
        assertEquals("GameService", event.getService());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getId());
        assertEquals(gameId, event.getGameId());
    }

    @Test
    void createGameEndedEvent() {
        UUID gameId = UUID.randomUUID();

        GameEndedEvent event = EventFactory.createGameEndedEvent(gameId);

        assertEquals("GameEnded", event.getType());
        assertEquals("GameService", event.getService());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getId());
        assertEquals(gameId, event.getGameId());
    }
}
