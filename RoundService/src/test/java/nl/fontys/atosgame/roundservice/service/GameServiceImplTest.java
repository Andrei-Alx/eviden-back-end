package nl.fontys.atosgame.roundservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.model.Game;
import nl.fontys.atosgame.roundservice.model.Lobby;
import nl.fontys.atosgame.roundservice.model.Round;
import nl.fontys.atosgame.roundservice.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.stream.function.StreamBridge;

class GameServiceImplTest {

    private RoundService roundService;
    private LobbyService lobbyService;
    private GameRepository gameRepository;
    private StreamBridge streamBridge;
    private GameServiceImpl gameService;

    @BeforeEach
    void setUp() {
        roundService = mock(RoundService.class);
        lobbyService = mock(LobbyService.class);
        gameRepository = mock(GameRepository.class);
        gameService =
            new GameServiceImpl(roundService, lobbyService, gameRepository);
    }

    @Test
    void initializeGame() {
        List<Round> rounds = mock(List.class);
        UUID gameId = UUID.randomUUID();
        doReturn(rounds).when(roundService).createRounds(gameId, null);
        when(gameRepository.save(any(Game.class))).thenAnswer(i -> i.getArguments()[0]);
        Lobby lobby = mock(Lobby.class);
        doReturn(Optional.of(lobby)).when(lobbyService).getLobbyByGameId(gameId);

        Game game = gameService.initializeGame(gameId, null);

        assertNotNull(game.getId());
        assertEquals(gameId, game.getId());
        assertEquals(lobby, game.getLobby());
        assertEquals(rounds, game.getRounds());
    }

    @Test
    void initializeGameWithNoLobby() {
        List<Round> rounds = mock(List.class);
        UUID gameId = UUID.randomUUID();
        doReturn(rounds).when(roundService).createRounds(gameId, null);
        when(gameRepository.save(any(Game.class))).thenAnswer(i -> i.getArguments()[0]);
        when(lobbyService.getLobbyByGameId(gameId)).thenReturn(Optional.empty());

        Game game = gameService.initializeGame(gameId, null);

        assertNotNull(game.getId());
        assertEquals(gameId, game.getId());
        assertNull(game.getLobby()); //test fails here. defective test?
        assertEquals(rounds, game.getRounds());
    }

    @Test
    void addLobbyToGameNormalFlow() {
        UUID gameId = UUID.randomUUID();
        Game game = new Game();
        game.setId(gameId);
        Lobby lobby = new Lobby();
        when(gameRepository.findById(gameId)).thenReturn(java.util.Optional.of(game));
        when(gameRepository.save(any(Game.class))).thenAnswer(i -> i.getArguments()[0]);

        Game updatedGame = gameService.addLobbyToGame(gameId, lobby);

        assertEquals(gameId, updatedGame.getId());
        assertEquals(lobby, updatedGame.getLobby());
    }

    @Test
    void addLobbyToGameGameNotFound() {
        UUID gameId = UUID.randomUUID();
        Lobby lobby = new Lobby();
        when(gameRepository.findById(gameId)).thenReturn(java.util.Optional.empty());

        assertThrows(
            IllegalArgumentException.class,
            () -> gameService.addLobbyToGame(gameId, lobby)
        );
    }

    @Test
    void startGame() {
        Round firstRound = mock(Round.class);
        List<Round> rounds = new ArrayList<>(List.of(firstRound, mock(Round.class)));
        Lobby lobby = mock(Lobby.class);
        Game game = new Game(UUID.randomUUID(), rounds, lobby);
        // Set behavior of repository
        when(gameRepository.findById(game.getId())).thenReturn(Optional.of(game));
        when(gameRepository.save(any(Game.class))).thenAnswer(i -> i.getArguments()[0]);
        // Set behavior of round service
        when(roundService.startRound(any(UUID.class), any(List.class), any(UUID.class)))
            .thenAnswer(i -> {
                // find round where parameter 0 is equal to the id of the round
                return rounds
                    .stream()
                    .filter(r -> r.getId().equals(i.getArguments()[0]))
                    .findFirst()
                    .orElseThrow();
            });

        // Act
        Game updatedGame = gameService.startGame(game.getId());

        // Assert
        verify(roundService)
            .startRound(firstRound.getId(), lobby.getPlayerIds(), game.getId());
        verify(gameRepository).save(updatedGame);
    }

    @Test
    void getGameByRoundId() {
        Game game = mock(Game.class);
        UUID roundId = UUID.randomUUID();
        when(gameRepository.getGameByRoundsId(roundId)).thenReturn(Optional.of(game));

        Optional<Game> result = gameService.getGameByRoundId(roundId);

        assertEquals(Optional.of(game), result);
    }
}
