package nl.fontys.atosgame.lobbyservice.event;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.UUID;
import nl.fontys.atosgame.lobbyservice.event.produced.LobbyCreatedEvent;
import nl.fontys.atosgame.lobbyservice.event.produced.LobbyDeletedEvent;
import nl.fontys.atosgame.lobbyservice.event.produced.PlayerJoinedEvent;
import nl.fontys.atosgame.lobbyservice.model.Lobby;
import org.junit.jupiter.api.Test;

class EventFactoryTest {

    @Test
    void createLobbyCreatedEvent() {
        Lobby lobby = mock(Lobby.class);
        UUID gameId = UUID.randomUUID();

        LobbyCreatedEvent lobbyCreatedEvent = EventFactory.createLobbyCreatedEvent(
            lobby,
            gameId
        );

        assertNotNull(lobbyCreatedEvent);
        assertNotNull(lobbyCreatedEvent.getId());
        assertNotNull(lobbyCreatedEvent.getTimestamp());
        assertEquals("LobbyCreated", lobbyCreatedEvent.getType());
        assertEquals("LobbyService", lobbyCreatedEvent.getService());
        assertEquals(lobby, lobbyCreatedEvent.getLobby());
        assertEquals(gameId, lobbyCreatedEvent.getGameId());
    }

    @Test
    void createLobbyDeletedEvent() {
        UUID id = UUID.randomUUID();
        UUID gameid = UUID.randomUUID();

        LobbyDeletedEvent lobbyDeletedEvent = EventFactory.createLobbyDeletedEvent(
            id,
            gameid
        );

        assertNotNull(lobbyDeletedEvent);
        assertNotNull(lobbyDeletedEvent.getId());
        assertNotNull(lobbyDeletedEvent.getTimestamp());
        assertEquals("LobbyDeleted", lobbyDeletedEvent.getType());
        assertEquals("LobbyService", lobbyDeletedEvent.getService());
        assertEquals(id, lobbyDeletedEvent.getLobbyId());
        assertEquals(gameid, lobbyDeletedEvent.getGameId());
    }

    @Test
    void createPlayerJoinedEvent() {
        UUID lobbyid = UUID.randomUUID();
        UUID playerid = UUID.randomUUID();
        UUID gameid = UUID.randomUUID();

        PlayerJoinedEvent playerJoinedEvent = EventFactory.createPlayerJoinedEvent(
            lobbyid,
            playerid,
            gameid
        );

        assertNotNull(playerJoinedEvent);
        assertNotNull(playerJoinedEvent.getId());
        assertNotNull(playerJoinedEvent.getTimestamp());
        assertEquals("PlayerJoined", playerJoinedEvent.getType());
        assertEquals("LobbyService", playerJoinedEvent.getService());
        assertEquals(lobbyid, playerJoinedEvent.getLobbyId());
        assertEquals(playerid, playerJoinedEvent.getPlayerId());
        assertEquals(gameid, playerJoinedEvent.getGameId());
    }

    @Test
    void createPlayerQuitEvent() {
        UUID lobbyid = UUID.randomUUID();
        UUID playerid = UUID.randomUUID();
        UUID gameid = UUID.randomUUID();

        PlayerJoinedEvent playerJoinedEvent = EventFactory.createPlayerJoinedEvent(
            lobbyid,
            playerid,
            gameid
        );

        assertNotNull(playerJoinedEvent);
        assertNotNull(playerJoinedEvent.getId());
        assertNotNull(playerJoinedEvent.getTimestamp());
        assertEquals("PlayerJoined", playerJoinedEvent.getType());
        assertEquals("LobbyService", playerJoinedEvent.getService());
        assertEquals(lobbyid, playerJoinedEvent.getLobbyId());
        assertEquals(playerid, playerJoinedEvent.getPlayerId());
        assertEquals(gameid, playerJoinedEvent.getGameId());
    }
}
