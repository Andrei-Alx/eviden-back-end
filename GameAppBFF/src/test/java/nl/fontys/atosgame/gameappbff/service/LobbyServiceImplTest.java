package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.controller.GameSocketController;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.repository.LobbyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class LobbyServiceImplTest {

    private LobbyRepository lobbyRepository;
    private LobbyServiceImpl lobbyService;
    private GameSocketController gameSocketController;

    @BeforeEach
    void setUp() {
        lobbyRepository = mock(LobbyRepository.class);
        gameSocketController = mock(GameSocketController.class);
        lobbyService = new LobbyServiceImpl(lobbyRepository, gameSocketController);
    }

    @Test
    void createLobby() {
        Lobby lobby = new Lobby();
        UUID gameId = UUID.randomUUID();

        Lobby result = lobbyService.createLobby(lobby, gameId);

        verify(lobbyRepository).save(lobby);
    }

    @Test
    void deleteLobby() {
        UUID lobbyId = UUID.randomUUID();

        lobbyService.deleteLobby(lobbyId);

        verify(lobbyRepository).deleteById(lobbyId);
    }
}