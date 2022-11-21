package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.controller.GameSocketController;
import nl.fontys.atosgame.gameappbff.enums.GameStatus;
import nl.fontys.atosgame.gameappbff.model.Game;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GameServiceImplTest {

    GameRepository gameRepository;
    StreamBridge streamBridge;
    GameServiceImpl gameService;
    GameSocketController gameSocketController;

    @BeforeEach
    void setUp() {
        gameRepository = mock(GameRepository.class);
        gameSocketController = mock(GameSocketController.class);
        streamBridge = mock(StreamBridge.class);
        gameService = new GameServiceImpl(gameRepository, gameSocketController);
    }

    @Test
    void createGame() {
        UUID gameId = UUID.randomUUID();
        when(gameRepository.save(any(Game.class))).thenAnswer(i -> i.getArguments()[0]);

        Game game = gameService.handleGameCreated(gameId);

        verify(gameRepository).save(game);
        assertEquals(GameStatus.CREATED, game.getStatus());
    }

    @Test
    void startGame() {
        UUID gameId = UUID.randomUUID();
        when(gameRepository.save(any(Game.class))).thenAnswer(i -> i.getArguments()[0]);
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(new Game(gameId, null, GameStatus.CREATED)));

        Game game = gameService.handleGameStarted(gameId);

        verify(gameRepository).save(game);
        assertEquals(GameStatus.STARTED, game.getStatus());
    }

    @Test
    void endGame() {
        UUID gameId = UUID.randomUUID();
        when(gameRepository.save(any(Game.class))).thenAnswer(i -> i.getArguments()[0]);
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(new Game(gameId, null, GameStatus.CREATED)));

        Game game = gameService.handleGameEnded(gameId);

        verify(gameRepository).save(game);
        assertEquals(GameStatus.ENDED, game.getStatus());
    }

    @Test
    void addLobbyToGame() {
        UUID gameId = UUID.randomUUID();
        Lobby lobby = mock(Lobby.class);
        when(gameRepository.save(any(Game.class))).thenAnswer(i -> i.getArguments()[0]);
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(new Game(gameId, null, GameStatus.CREATED)));

        Game game = gameService.addLobbyToGame(gameId, lobby);

        verify(gameRepository).save(game);
        assertEquals(lobby, game.getLobby());
    }
}
