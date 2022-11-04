package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.model.Lobby;
import nl.fontys.atosgame.roundservice.repository.LobbyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LobbyServiceImplTest {

    private LobbyRepository lobbyRepository;
    private LobbyServiceImpl lobbyService;

    @BeforeEach
    void setUp() {
        lobbyRepository = mock(LobbyRepository.class);
        lobbyService = spy(new LobbyServiceImpl(lobbyRepository));
    }

    @Test
    void addPlayerToLobbyNormalFlow() {
        UUID playerId = UUID.randomUUID();
        UUID lobbyId = UUID.randomUUID();
        Lobby lobby = new Lobby();
        lobby.setId(lobbyId);
        when(lobbyRepository.findById(lobbyId)).thenReturn(Optional.of(lobby));
        when(lobbyRepository.save(any(Lobby.class))).thenReturn(lobby);

        Lobby result = lobbyService.addPlayerToLobby(playerId, lobbyId);

        verify(lobbyRepository, times(1)).save(lobby);
        assertEquals(1, result.getPlayerIds().size());
        assertTrue(result.getPlayerIds().contains(playerId));
        assertEquals(lobbyId, result.getId());
    }

    @Test
    void addPlayerToLobbyLobbyNotFound() {
        UUID playerId = UUID.randomUUID();
        UUID lobbyId = UUID.randomUUID();
        when(lobbyRepository.findById(lobbyId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> lobbyService.addPlayerToLobby(playerId, lobbyId));
    }

    @Test
    void removePlayerFromLobbyNormalFlow() {
        UUID playerId = UUID.randomUUID();
        UUID lobbyId = UUID.randomUUID();
        Lobby lobby = new Lobby();
        lobby.setId(lobbyId);
        lobby.getPlayerIds().add(playerId);
        when(lobbyRepository.findById(lobbyId)).thenReturn(Optional.of(lobby));
        when(lobbyRepository.save(any(Lobby.class))).thenReturn(lobby);

        Lobby result = lobbyService.removePlayerFromLobby(playerId, lobbyId);

        verify(lobbyRepository, times(1)).save(lobby);
        assertEquals(0, result.getPlayerIds().size());
        assertFalse(result.getPlayerIds().contains(playerId));
        assertEquals(lobbyId, result.getId());
    }

    @Test
    void removePlayerFromLobbyLobbyNotFound() {
        UUID playerId = UUID.randomUUID();
        UUID lobbyId = UUID.randomUUID();
        when(lobbyRepository.findById(lobbyId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> lobbyService.removePlayerFromLobby(playerId, lobbyId));
    }
}