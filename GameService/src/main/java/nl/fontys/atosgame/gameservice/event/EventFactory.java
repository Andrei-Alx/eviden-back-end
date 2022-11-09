package nl.fontys.atosgame.gameservice.event;

import nl.fontys.atosgame.gameservice.event.produced.GameCreatedEvent;
import nl.fontys.atosgame.gameservice.model.LobbySettings;
import nl.fontys.atosgame.gameservice.model.RoundSettings;

import java.util.List;
import java.util.UUID;

/**
 * Factory class for creating BaseEvent objects
 * @author Eli
 */
public class EventFactory {

    public static GameCreatedEvent createGameCreatedEvent(UUID gameId, List<RoundSettings> roundSettings, LobbySettings lobbySettings) {
        GameCreatedEvent event = new GameCreatedEvent();
        event = (GameCreatedEvent) initializeBaseEvent(event, "GameCreated", "GameService");
        event.setGameId(gameId);
        event.setRoundSettings(roundSettings);
        event.setLobbySettings(lobbySettings);
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
