package nl.fontys.atosgame.gameappbff.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

import nl.fontys.atosgame.gameappbff.controller.GameSocketController;
import nl.fontys.atosgame.gameappbff.enums.GameStatus;
import nl.fontys.atosgame.gameappbff.model.Game;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.model.Round;
import nl.fontys.atosgame.gameappbff.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.stream.function.StreamBridge;

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

        Game game = gameService.handleGameCreated(gameId, "testGame", "testCompanyType");

        verify(gameRepository).save(game);
        assertEquals(GameStatus.CREATED, game.getStatus());
        assertEquals(gameId, game.getId());
        assertEquals("testGame", game.getTitle());
        assertEquals("testCompanyType", game.getCompanyType());
    }

    @Test
    void startGame() {
        UUID gameId = UUID.randomUUID();
        when(gameRepository.save(any(Game.class))).thenAnswer(i -> i.getArguments()[0]);
        when(gameRepository.findById(gameId))
            .thenReturn(Optional.of(new Game(gameId, "testGame", null, "testCompany", GameStatus.CREATED, null)));

        Game game = gameService.handleGameStarted(gameId);

        verify(gameRepository).save(game);
        assertEquals(GameStatus.STARTED, game.getStatus());
    }

    @Test
    void endGame() {
        UUID gameId = UUID.randomUUID();
        when(gameRepository.save(any(Game.class))).thenAnswer(i -> i.getArguments()[0]);
        when(gameRepository.findById(gameId))
            .thenReturn(Optional.of(new Game(gameId, "testGame", null, "testCompany", GameStatus.CREATED, null)));

        Game game = gameService.handleGameEnded(gameId);

        verify(gameRepository).save(game);
        assertEquals(GameStatus.ENDED, game.getStatus());
    }

    @Test
    void addLobbyToGame() {
        UUID gameId = UUID.randomUUID();
        Lobby lobby = mock(Lobby.class);
        when(gameRepository.save(any(Game.class))).thenAnswer(i -> i.getArguments()[0]);
        when(gameRepository.findById(gameId))
            .thenReturn(Optional.of(new Game(gameId, "testGame", null, "testCompany", GameStatus.CREATED, null)));

        Game game = gameService.addLobbyToGame(gameId, lobby);

        verify(gameRepository).save(game);
        assertEquals(lobby, game.getLobby());
    }

    @Test
    void addRoundToGame() {
        UUID gameId = UUID.randomUUID();
        Round round = mock(Round.class);
        Game game = new Game();
        when(gameRepository.save(any(Game.class))).thenAnswer(i -> i.getArguments()[0]);
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));

        gameService.addRoundToGame(round, gameId);

        verify(gameRepository).save(game);
        assertEquals(round, game.getRounds().get(0));
    }

    @Test
    void getGame() {
        UUID gameId = UUID.randomUUID();
        Game game = new Game();
        game.setId(gameId);
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));

        Optional<Game> result = gameService.getGame(gameId);

        assertEquals(game, result.get());
    }
}
