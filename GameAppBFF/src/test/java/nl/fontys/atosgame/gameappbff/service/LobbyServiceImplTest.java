package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.repository.LobbyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        assertNotNull(result);
        verify(lobbyRepository).save(lobby);
    }
}