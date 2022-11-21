package nl.fontys.atosgame.gameappbff.controller;

import nl.fontys.atosgame.gameappbff.event.consumed.LobbyCreatedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.LobbyDeletedEvent;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.service.GameService;
import nl.fontys.atosgame.gameappbff.service.LobbyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class LobbyConsumersTest {

    private GameService gameService;

    private LobbyService lobbyService;
    private LobbyConsumers lobbyConsumers;

    @BeforeEach
    void setUp() {
        gameService = mock(GameService.class);
        lobbyService = mock(LobbyService.class);
        lobbyConsumers = new LobbyConsumers(gameService, lobbyService);
    }

    @Test
    void handleLobbyCreated() {
        Message<LobbyCreatedEvent> message = mock(Message.class);
        LobbyCreatedEvent lobbyCreatedEvent = mock(LobbyCreatedEvent.class);
        doReturn(lobbyCreatedEvent).when(message).getPayload();
        Lobby lobby = mock(Lobby.class);
        doReturn(lobby).when(lobbyCreatedEvent).getLobby();
        UUID gameId = UUID.randomUUID();
        when(lobbyService.createLobby(any(Lobby.class), gameId)).thenAnswer(i -> i.getArguments()[0]);
        doReturn(gameId).when(lobbyCreatedEvent).getGameId();

        lobbyConsumers.handleLobbyCreated().apply(message);

        verify(lobbyService).createLobby(lobby, gameId);
        verify(gameService).addLobbyToGame(gameId, lobby);
    }
}