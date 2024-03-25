package nl.fontys.atosgame.lobbyservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import nl.fontys.atosgame.lobbyservice.controller.produced.LobbyEventProducers;
import nl.fontys.atosgame.lobbyservice.dto.LobbyDeletedDto;
import nl.fontys.atosgame.lobbyservice.dto.LobbyJoinedDto;
import nl.fontys.atosgame.lobbyservice.event.produced.LobbyCreatedEvent;
import nl.fontys.atosgame.lobbyservice.event.produced.LobbyDeletedEvent;
import nl.fontys.atosgame.lobbyservice.event.produced.PlayerJoinedEvent;
import nl.fontys.atosgame.lobbyservice.model.Lobby;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;

class LobbyEventProducersTest {

    @Test
    void produceLobbyCreated() {
        Lobby lobby = mock(Lobby.class);
        LobbyEventProducers lobbyEventProducers = new LobbyEventProducers();

        Message<LobbyCreatedEvent> message = lobbyEventProducers
            .produceLobbyCreated()
            .apply(lobby);

        assertEquals(lobby, message.getPayload().getLobby());
        assertEquals(lobby.getGameId(), message.getPayload().getGameId());
        assertEquals(
            lobby.getGameId(),
            message.getHeaders().get(KafkaHeaders.KEY)
        );
    }

    @Test
    void produceLobbyDeleted() {
        LobbyDeletedDto lobbyDeletedDto = mock(LobbyDeletedDto.class);
        LobbyEventProducers lobbyEventProducers = new LobbyEventProducers();

        Message<LobbyDeletedEvent> message = lobbyEventProducers
            .produceLobbyDeleted()
            .apply(lobbyDeletedDto);

        assertEquals(lobbyDeletedDto.getLobbyId(), message.getPayload().getLobbyId());
        assertEquals(lobbyDeletedDto.getGameId(), message.getPayload().getGameId());
        assertEquals(
            lobbyDeletedDto.getGameId(),
            message.getHeaders().get(KafkaHeaders.KEY)
        );
    }

    @Test
    void producePlayerJoined() {
        LobbyJoinedDto lobbyJoinedDto = mock(LobbyJoinedDto.class);
        LobbyEventProducers lobbyEventProducers = new LobbyEventProducers();

        Message<PlayerJoinedEvent> message = lobbyEventProducers
            .producePlayerJoined()
            .apply(lobbyJoinedDto);

        assertEquals(lobbyJoinedDto.getLobbyId(), message.getPayload().getLobbyId());
        assertEquals(lobbyJoinedDto.getGameId(), message.getPayload().getGameId());
        assertEquals(
            lobbyJoinedDto.getRequestedPlayer(),
            message.getPayload().getPlayer()
        );
        assertEquals(
            lobbyJoinedDto.getGameId(),
            message.getHeaders().get(KafkaHeaders.KEY)
        );
    }

    @Test
    void producePlayerQuit() {
        LobbyJoinedDto lobbyJoinedDto = mock(LobbyJoinedDto.class);
        LobbyEventProducers lobbyEventProducers = new LobbyEventProducers();

        Message<PlayerJoinedEvent> message = lobbyEventProducers
            .producePlayerJoined()
            .apply(lobbyJoinedDto);

        assertEquals(lobbyJoinedDto.getLobbyId(), message.getPayload().getLobbyId());
        assertEquals(lobbyJoinedDto.getGameId(), message.getPayload().getGameId());
        assertEquals(
            lobbyJoinedDto.getRequestedPlayer(),
            message.getPayload().getPlayer()
        );
        assertEquals(
            lobbyJoinedDto.getGameId(),
            message.getHeaders().get(KafkaHeaders.KEY)
        );
    }
}
