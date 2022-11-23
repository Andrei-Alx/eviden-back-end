package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.controller.GameSocketController;
import nl.fontys.atosgame.gameappbff.controller.LobbySocketController;
import nl.fontys.atosgame.gameappbff.model.Player;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.repository.LobbyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class LobbyServiceImplTest {

    private LobbyRepository lobbyRepository;
    private LobbyServiceImpl lobbyService;
    private GameSocketController gameSocketController;
    private LobbySocketController lobbySocketController;

    @BeforeEach
    void setUp() {
        lobbyRepository = mock(LobbyRepository.class);
        gameSocketController = mock(GameSocketController.class);
        lobbySocketController = mock(LobbySocketController.class);
        lobbyService = new LobbyServiceImpl(lobbyRepository, gameSocketController, lobbySocketController);
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

    @Test
    void addPlayerTest() {
        UUID lobbyId = UUID.randomUUID();
        Player player = new Player();
        player.setId(UUID.randomUUID());
        player.setName("test");
        Lobby lobby = new Lobby();
        lobby.setId(lobbyId);
        lobbyRepository.save(lobby);

        Lobby result = lobbyService.addPlayer(lobbyId, player);

        verify(lobbyRepository).save(lobby);
    }
}
