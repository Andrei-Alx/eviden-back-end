package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameServiceImplTest {

    GameRepository gameRepository;
    StreamBridge streamBridge;
    GameServiceImpl gameService;

    @BeforeEach
    void setUp() {
        gameRepository = mock(GameRepository.class);
        streamBridge = mock(StreamBridge.class);
        gameService = new GameServiceImpl(gameRepository);
    }

    @Test
    void createGame() {
        UUID gameId = UUID.randomUUID();

        gameService.handleGameCreated(gameId);

        verify(gameRepository).save(gameId);
    }
}
