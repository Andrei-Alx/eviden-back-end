package nl.fontys.atosgame.gameappbff.controller;

import static org.mockito.Mockito.*;

import java.util.UUID;
import nl.fontys.atosgame.gameappbff.event.consumed.LobbyCreatedEvent;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.service.GameService;
import nl.fontys.atosgame.gameappbff.service.LobbyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;

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
        LobbyCreatedEvent lobbyCreatedEvent = mock(LobbyCreatedEvent.class);
        Message<LobbyCreatedEvent> message = mock(Message.class);
        doReturn(lobbyCreatedEvent).when(message).getPayload();
        Lobby lobby = mock(Lobby.class);
        doReturn(lobby).when(lobbyCreatedEvent).getLobby();
        UUID gameId = UUID.randomUUID();
        when(lobbyService.createLobby(any(Lobby.class), eq(gameId))).thenAnswer(i -> i.getArguments()[0]);
        doReturn(gameId).when(lobbyCreatedEvent).getGameId();

        lobbyConsumers.handleLobbyCreated().apply(message);

        verify(lobbyService).createLobby(lobby, gameId);
        verify(gameService).addLobbyToGame(gameId, lobby);
    }
}