package nl.fontys.atosgame.lobbyservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;
import nl.fontys.atosgame.lobbyservice.model.Lobby;
import nl.fontys.atosgame.lobbyservice.model.LobbySettings;
import nl.fontys.atosgame.lobbyservice.repository.LobbyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LobbyServiceImplTest {

    LobbyRepository lobbyRepository;
    LobbyCodeGenerator lobbyCodeGenerator;
    LobbyServiceImpl lobbyService;

    @BeforeEach
    void setUp() {
        lobbyRepository = mock(LobbyRepository.class);
        lobbyCodeGenerator = mock(LobbyCodeGenerator.class);
        lobbyService = new LobbyServiceImpl(lobbyRepository, lobbyCodeGenerator);
    }

    @Test
    void createLobby() {
        LobbySettings settings = new LobbySettings();
        UUID gameId = UUID.randomUUID();
        when(lobbyRepository.save(any(Lobby.class)))
            .thenAnswer(i -> {
                Lobby lobby = i.getArgument(0);
                lobby.setId(UUID.randomUUID());
                return i.getArguments()[0];
            });
        when(lobbyCodeGenerator.generateLobbyCode()).thenReturn("ABCDEF");

        Lobby lobby = lobbyService.createLobby(settings, gameId);

        assertEquals(settings, lobby.getLobbySettings());
        assertEquals(gameId, lobby.getGameId());
        assertEquals("ABCDEF", lobby.getLobbyCode());
        assertNotNull(lobby.getPlayers());
        assertTrue(lobby.getPlayers().isEmpty());
        assertNotNull(lobby.getId());
    }

    @Test
    void deleteLobbyByGameId() {
        UUID gameId = UUID.randomUUID();

        lobbyService.deleteLobbyByGameId(gameId);

        verify(lobbyRepository).deleteByGameId(gameId);
    }
}
