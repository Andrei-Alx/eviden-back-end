package nl.fontys.atosgame.gameservice.controllers;

import nl.fontys.atosgame.gameservice.dto.CreateGameEventDto;
import nl.fontys.atosgame.gameservice.event.produced.GameCreatedEvent;
import nl.fontys.atosgame.gameservice.model.LobbySettings;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GameProducersTest {

    @Test
    void produceGameCreated() {
        CreateGameEventDto dto = new CreateGameEventDto(
            UUID.randomUUID(),
            "companyType",
            new ArrayList<>(),
            new LobbySettings()
        );
        GameProducers gameProducers = new GameProducers();

        Message<GameCreatedEvent> event = gameProducers.produceGameCreated().apply(dto);

        assertEquals("GameCreated", event.getPayload().getType());
        assertEquals("GameService", event.getPayload().getService());
        assertNotNull(event.getPayload().getTimestamp());
        assertNotNull(event.getPayload().getId());
        assertEquals(dto.getGameId(), event.getPayload().getGameId());
        assertEquals(dto.getCompanyType(), event.getPayload().getCompanyType());
        assertEquals(dto.getRoundSettings(), event.getPayload().getRoundSettings());
        assertEquals(dto.getLobbySettings(), event.getPayload().getLobbySettings());
    }
}