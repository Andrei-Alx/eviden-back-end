package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.model.Game;
import nl.fontys.atosgame.roundservice.model.Lobby;
import nl.fontys.atosgame.roundservice.model.Round;
import nl.fontys.atosgame.roundservice.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameServiceImplTest {

    private RoundService roundService;
    private GameRepository gameRepository;
    private GameServiceImpl gameService;

    @BeforeEach
    void setUp() {
        roundService = mock(RoundService.class);
        gameRepository = mock(GameRepository.class);
        gameService = new GameServiceImpl(roundService, gameRepository);
    }

    @Test
    void initializeGame() {
        List<Round> rounds = mock(List.class);
        UUID gameId = UUID.randomUUID();
        doReturn(rounds).when(roundService).createRounds(gameId, null);
        when(gameRepository.save(any(Game.class))).thenAnswer(i -> i.getArguments()[0]);

        Game game = gameService.initializeGame(gameId, null);

        assertNotNull(game.getId());
        assertEquals(gameId, game.getId());
        assertNull(game.getLobby());
        assertEquals(0, game.getAmountOfPlayers());
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

        assertThrows(IllegalArgumentException.class, () -> gameService.addLobbyToGame(gameId, lobby));
    }
}