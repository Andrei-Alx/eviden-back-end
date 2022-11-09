package nl.fontys.atosgame.lobbyservice.event;


import nl.fontys.atosgame.lobbyservice.event.produced.LobbyCreatedEvent;
import nl.fontys.atosgame.lobbyservice.event.produced.LobbyDeletedEvent;
import nl.fontys.atosgame.lobbyservice.event.produced.PlayerJoinedEvent;
import nl.fontys.atosgame.lobbyservice.event.produced.PlayerQuitEvent;
import nl.fontys.atosgame.lobbyservice.model.Lobby;

import java.util.UUID;

/**
 * Factory class for creating events
 * @author Aniek
 */
public class EventFactory {

    public static LobbyCreatedEvent createLobbyCreatedEvent(Lobby lobby) {
        LobbyCreatedEvent event = new LobbyCreatedEvent();
        event = (LobbyCreatedEvent) initializeBaseEvent(event, "LobbyCreated","LobbyService");
        event.setLobby(lobby);
        return event;
    }

    public static LobbyDeletedEvent createLobbyDeletedEvent(UUID lobbyId, UUID gameId) {
        LobbyDeletedEvent event = new LobbyDeletedEvent();
        event = (LobbyDeletedEvent) initializeBaseEvent(event, "LobbyDeleted","LobbyService");
        event.setLobbyId(lobbyId);
        event.setGameId(gameId);
        return event;
    }

    public static PlayerJoinedEvent createPlayerJoinedEvent(UUID lobbyId, UUID playerId, UUID gameId) {
        PlayerJoinedEvent event = new PlayerJoinedEvent();
        event = (PlayerJoinedEvent) initializeBaseEvent(event, "PlayerJoined","LobbyService");
        event.setLobbyId(lobbyId);
        event.setPlayerId(playerId);
        event.setGameId(gameId);
        return event;
    }

    public static PlayerQuitEvent createPlayerQuitEvent(UUID lobbyId, UUID playerId, UUID gameId) {
        PlayerQuitEvent event = new PlayerQuitEvent();
        event = (PlayerQuitEvent) initializeBaseEvent(event, "PlayerQuit","LobbyService");
        event.setLobbyId(lobbyId);
        event.setPlayerId(playerId);
        event.setGameId(gameId);
        return event;
    }

    private static BaseEvent initializeBaseEvent(BaseEvent event, String type, String service) {
        event.setTimestamp(java.time.LocalDateTime.now());
        event.setId(UUID.randomUUID());
        event.setType(type);
        event.setService(service);
        return event;
    }
}
