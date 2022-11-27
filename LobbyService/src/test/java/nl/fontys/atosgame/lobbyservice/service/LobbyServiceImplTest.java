package nl.fontys.atosgame.lobbyservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import java.util.*;

import nl.fontys.atosgame.lobbyservice.exceptions.DuplicatePlayerException;
import nl.fontys.atosgame.lobbyservice.exceptions.LobbyFullException;
import nl.fontys.atosgame.lobbyservice.dto.LobbyDeletedDto;
import nl.fontys.atosgame.lobbyservice.dto.LobbyJoinedDto;
import nl.fontys.atosgame.lobbyservice.dto.LobbyQuitDto;
import nl.fontys.atosgame.lobbyservice.model.Lobby;
import nl.fontys.atosgame.lobbyservice.model.LobbySettings;
import nl.fontys.atosgame.lobbyservice.model.Player;
import nl.fontys.atosgame.lobbyservice.repository.LobbyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.util.Assert;

class LobbyServiceImplTest {

    LobbyRepository lobbyRepository;
    LobbyCodeGenerator lobbyCodeGenerator;
    StreamBridge streamBridge;
    LobbyServiceImpl lobbyService;

    @BeforeEach
    void setUp() {
        lobbyRepository = mock(LobbyRepository.class);
        lobbyCodeGenerator = mock(LobbyCodeGenerator.class);
        streamBridge = mock(StreamBridge.class);
        lobbyService =
            new LobbyServiceImpl(lobbyRepository, lobbyCodeGenerator, streamBridge);
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
        verify(streamBridge).send("produceLobbyCreated-in-0", lobby);
    }

    @Test
    void deleteLobbyByGameId() {
        UUID gameId = UUID.randomUUID();
        Lobby lobby = mock(Lobby.class);
        when(lobbyRepository.getByGameId(gameId)).thenReturn(lobby);

        lobbyService.deleteLobbyByGameId(gameId);

        verify(lobbyRepository).deleteByGameId(gameId);
        verify(streamBridge)
            .send("produceLobbyDeleted-in-0", new LobbyDeletedDto(lobby.getId(), gameId));
    }

    @Test
    void joinLobby() throws Exception {
        when(lobbyCodeGenerator.generateLobbyCode()).thenReturn("ABCDEF");
        // arrange player and lobby
        String lobbyCode = lobbyCodeGenerator.generateLobbyCode();
        String playerName = "PlayerOne";

        LobbySettings lobbySettings = new LobbySettings(8);
        Lobby lobby = new Lobby(UUID.randomUUID(), new ArrayList<Player>(),lobbyCode, lobbySettings, UUID.randomUUID());
        // arrange mock
        when(lobbyRepository.getByLobbyCode(lobbyCode)).thenReturn(lobby);


        // act
        Lobby joinedLobby = lobbyService.joinLobby(lobbyCode,playerName);
        LobbyJoinedDto lobbyJoinedDto = new LobbyJoinedDto(joinedLobby.getId(), joinedLobby.getPlayers(), joinedLobby.getPlayers().stream().findFirst().get(), joinedLobby.getLobbyCode(), joinedLobby.getGameId());

        //assert
        assertEquals("ABCDEF", joinedLobby.getLobbyCode());
        assertEquals(1, joinedLobby.getPlayers().size());
        assertEquals("PlayerOne", joinedLobby.getPlayers().stream().findFirst().get().getName());
        verify(lobbyRepository, times(1)).getByLobbyCode(lobbyCode);
        verify(streamBridge)
                .send("producePlayerJoined-in-0", lobbyJoinedDto);
    }

    @Test
    void joinLobbyFullThrowsException() {
        when(lobbyCodeGenerator.generateLobbyCode()).thenReturn("ABCDEF");
        // arrange player and lobby
        String lobbyCode = lobbyCodeGenerator.generateLobbyCode();
        String playerName = "PlayerTwo";
        Player player = new Player(UUID.randomUUID(), "PlayerOne");
        LobbySettings lobbySettings = new LobbySettings(1);
        Lobby lobby = new Lobby(UUID.randomUUID(), new ArrayList<Player>(List.of(player)),lobbyCode, lobbySettings, UUID.randomUUID());
        // arrange mock
        when(lobbyRepository.getByLobbyCode(lobbyCode)).thenReturn(lobby);

        // act
        assertThrows(LobbyFullException.class, () -> lobbyService.joinLobby(lobbyCode,playerName));
    }

    @Test
    void joinLobbyAlreadyJoinedThrowsException() {
        when(lobbyCodeGenerator.generateLobbyCode()).thenReturn("ABCDEF");
        // arrange player and lobby
        String lobbyCode = lobbyCodeGenerator.generateLobbyCode();
        String playerName = "PlayerOne";
        Player player = new Player(UUID.randomUUID(), "PlayerOne");
        LobbySettings lobbySettings = new LobbySettings(2);
        Lobby lobby = new Lobby(UUID.randomUUID(), new ArrayList<Player>(List.of(player)),lobbyCode, lobbySettings, UUID.randomUUID());
        // arrange mock
        when(lobbyRepository.getByLobbyCode(lobbyCode)).thenReturn(lobby);

        // act
        assertThrows(DuplicatePlayerException.class, () -> lobbyService.joinLobby(lobbyCode,playerName));
    }

    @Test
    void quitLobby(){
        // arrange
        UUID lobbyId = UUID.randomUUID();
        String lobbyCode = lobbyCodeGenerator.generateLobbyCode();
        LobbySettings lobbySettings = new LobbySettings(8);
        String playerName = "PlayerOne";
        UUID playerId = UUID.randomUUID();
        Collection<Player> players = new ArrayList<Player>();
        players.add(new Player(playerId, playerName));
        Lobby lobby = new Lobby(lobbyId, players,lobbyCode, lobbySettings, UUID.randomUUID());
        when(lobbyRepository.getById(any(UUID.class))).thenReturn(lobby);
        // act
        lobbyService.quitLobby(lobbyId, playerId);
        // assert
        Assert.isTrue(lobby.getPlayers().stream().count() == 0, "player has been successfully removed from the lobby");
        LobbyQuitDto lobbyQuitDto = new LobbyQuitDto(lobbyId, playerId, lobby.getGameId());
        verify(streamBridge)
                .send("producePlayerQuit-in-0", lobbyQuitDto);
    }
}
