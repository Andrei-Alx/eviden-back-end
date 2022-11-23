package nl.fontys.atosgame.gameservice.event;

import java.util.List;
import java.util.UUID;
import nl.fontys.atosgame.gameservice.event.produced.GameCreatedEvent;
import nl.fontys.atosgame.gameservice.event.produced.GameEndedEvent;
import nl.fontys.atosgame.gameservice.event.produced.GameStartedEvent;
import nl.fontys.atosgame.gameservice.model.LobbySettings;
import nl.fontys.atosgame.gameservice.model.RoundSettings;

/**
 * Factory class for creating BaseEvent objects
 * @author Eli
 */
public class EventFactory {

    public static GameCreatedEvent createGameCreatedEvent(
        UUID gameId,
        String companyType,
        List<RoundSettings> roundSettings,
        LobbySettings lobbySettings
    ) {
        GameCreatedEvent event = new GameCreatedEvent();
        event =
            (GameCreatedEvent) initializeBaseEvent(event, "GameCreated", "GameService");
        event.setGameId(gameId);
        event.setCompanyType(companyType);
        event.setRoundSettings(roundSettings);
        event.setLobbySettings(lobbySettings);
        return event;
    }

    public static GameStartedEvent createGameStartedEvent(UUID gameId) {
        GameStartedEvent event = new GameStartedEvent();
        event =
            (GameStartedEvent) initializeBaseEvent(event, "GameStarted", "GameService");
        event.setGameId(gameId);
        return event;
    }

    public static GameEndedEvent createGameEndedEvent(UUID gameId) {
        GameEndedEvent event = new GameEndedEvent();
        event = (GameEndedEvent) initializeBaseEvent(event, "GameEnded", "GameService");
        event.setGameId(gameId);
        return event;
    }

    private static BaseEvent initializeBaseEvent(
        BaseEvent event,
        String type,
        String service
    ) {
        event.setTimestamp(java.time.LocalDateTime.now());
        event.setId(UUID.randomUUID());
        event.setType(type);
        event.setService(service);
        return event;
    }
}
