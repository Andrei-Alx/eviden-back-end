package nl.fontys.atosgame.gameappbff.service;

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

    @BeforeEach
    void setUp() {
        lobbyRepository = mock(LobbyRepository.class);
        lobbyService = new LobbyServiceImpl(lobbyRepository);
    }

    @Test
    void createLobby() {
        Lobby lobby = new Lobby();

        Lobby result = lobbyService.createLobby(lobby);

        verify(lobbyRepository).save(lobby);
    }

    @Test
    void deleteLobby() {
        UUID lobbyId = UUID.randomUUID();

        lobbyService.deleteLobby(lobbyId);

        verify(lobbyRepository).deleteById(lobbyId);
    }
}