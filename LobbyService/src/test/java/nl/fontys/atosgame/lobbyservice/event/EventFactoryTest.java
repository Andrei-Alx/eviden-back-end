package nl.fontys.atosgame.lobbyservice.event;

import nl.fontys.atosgame.lobbyservice.event.produced.LobbyCreatedEvent;
import nl.fontys.atosgame.lobbyservice.event.produced.LobbyDeletedEvent;
import nl.fontys.atosgame.lobbyservice.model.Lobby;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class EventFactoryTest {

    @Test
    void createLobbyCreatedEvent() {
        Lobby lobby = mock(Lobby.class);

        LobbyCreatedEvent lobbyCreatedEvent = EventFactory.createLobbyCreatedEvent(lobby);

        assertNotNull(lobbyCreatedEvent);
        assertNotNull(lobbyCreatedEvent.getId());
        assertNotNull(lobbyCreatedEvent.getTimestamp());
        assertEquals("LobbyCreated", lobbyCreatedEvent.getType());
        assertEquals("LobbyService", lobbyCreatedEvent.getService());
        assertEquals(lobby, lobbyCreatedEvent.getLobby());
    }

    @Test
    void createLobbyDeletedEvent() {
        UUID id = UUID.randomUUID();
        UUID gameid = UUID.randomUUID();

        LobbyDeletedEvent lobbyDeletedEvent = EventFactory.createLobbyDeletedEvent(id, gameid);

        assertNotNull(lobbyDeletedEvent);
        assertNotNull(lobbyDeletedEvent.getId());
        assertNotNull(lobbyDeletedEvent.getTimestamp());
        assertEquals("LobbyDeleted", lobbyDeletedEvent.getType());
        assertEquals("Test", lobbyDeletedEvent.getService());
        assertEquals(id, lobbyDeletedEvent.getLobbyId());
    }
}