package nl.fontys.atosgame.gameservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.gameservice.dto.CreateGameEventDto;
import nl.fontys.atosgame.gameservice.enums.GameStatus;
import nl.fontys.atosgame.gameservice.enums.ShowResults;
import nl.fontys.atosgame.gameservice.enums.ShuffleMethod;
import nl.fontys.atosgame.gameservice.model.*;
import nl.fontys.atosgame.gameservice.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.stream.function.StreamBridge;

class GameServiceImplTest {

    private GameRepository gameRepository;
    private CardSetService cardSetService;
    private StreamBridge streamBridge;
    private GameServiceImpl gameService;

    @BeforeEach
    void setUp() {
        gameRepository = mock(GameRepository.class);
        cardSetService = mock(CardSetService.class);
        streamBridge = mock(StreamBridge.class);
        gameService = new GameServiceImpl(gameRepository, cardSetService, streamBridge);
    }

    @Test
    void createGameCardSetsExist() {
        String title = "titleGame";
        String companyType = "companyType";
        LobbySettings lobbySettings = new LobbySettings();
        List<RoundSettings> roundSettings = new ArrayList<>(
            List.of(
                new RoundSettings(
                    ShowResults.PERSONAL,
                    1,
                    1,
                    ShuffleMethod.FULLY_RANDOM,
                    false,
                    UUID.randomUUID()
                ),
                new RoundSettings(
                    ShowResults.PERSONAL,
                    1,
                    1,
                    ShuffleMethod.FULLY_RANDOM,
                    false,
                    UUID.randomUUID()
                )
            )
        );
        when(cardSetService.getCardSet(any()))
            .thenReturn(Optional.of(mock(CardSet.class)));
        when(gameRepository.save(any()))
            .thenAnswer(invocation -> {
                Game game = invocation.getArgument(0);
                game.setId(UUID.randomUUID());
                return game;
            });

        Game result = gameService.createGame(
            "titleGame",
            "companyType",
            lobbySettings,
            roundSettings
        );

        assertNotNull(result.getId());
        assertEquals(title, result.getTitle());
        assertEquals(companyType, result.getCompanyType());
        assertNull(result.getLobby());
//        assertNull(result.getRounds());
        assertEquals(0, result.getRounds().size());
        assertEquals(GameStatus.CREATED, result.getStatus());
        verify(gameRepository, times(1)).save(any());
        verify(streamBridge, times(1))
            .send(
                "produceGameCreated-in-0",
                new CreateGameEventDto(
                    result.getId(),
                    title,
                    companyType,
                    roundSettings,
                    lobbySettings
                )
            );
    }

    @Test
    void createGameCardSetsDoNotExist() {
        LobbySettings lobbySettings = new LobbySettings();
        List<RoundSettings> roundSettings = new ArrayList<>(
            List.of(
                new RoundSettings(
                    ShowResults.PERSONAL,
                    1,
                    1,
                    ShuffleMethod.FULLY_RANDOM,
                    false,
                    UUID.randomUUID()
                ),
                new RoundSettings(
                    ShowResults.PERSONAL,
                    1,
                    1,
                    ShuffleMethod.FULLY_RANDOM,
                    false,
                    UUID.randomUUID()
                )
            )
        );
        when(cardSetService.getCardSet(any())).thenReturn(Optional.empty());

        assertThrows(
            IllegalArgumentException.class,
            () ->
                gameService.createGame(
                    "titleGame",
                    "companyType",
                    lobbySettings,
                    roundSettings
                )
        );
    }

    @Test
    void startGame() {
        Game game = new Game();
        game.setId(UUID.randomUUID());
        when(gameRepository.findById(any())).thenReturn(Optional.of(game));
        when(gameRepository.save(any()))
            .thenAnswer(invocation -> invocation.getArgument(0));

        Game result = gameService.startGame(game.getId());
        verify(gameRepository, times(1)).save(any());
        assertEquals(GameStatus.STARTED, result.getStatus());
        verify(streamBridge, times(1)).send("produceGameStarted-in-0", game.getId());
    }

    @Test
    void addRound() {
        Game game = new Game();
        game.setId(UUID.randomUUID());
        when(gameRepository.findById(game.getId())).thenReturn(Optional.of(game));
        when(gameRepository.save(any()))
            .thenAnswer(invocation -> invocation.getArgument(0));
        Round round = new Round();

        Game result = gameService.addRound(game.getId(), round);

        assertTrue(result.getRounds().contains(round));
        assertEquals(game.getId(), result.getId());
    }

    @Test
    void endGame() {
        Game game = new Game();
        game.setId(UUID.randomUUID());
        when(gameRepository.findById(game.getId())).thenReturn(Optional.of(game));
        when(gameRepository.save(any()))
            .thenAnswer(invocation -> invocation.getArgument(0));

        Game result = gameService.endGame(game.getId());

        assertEquals(GameStatus.ENDED, result.getStatus());
        verify(streamBridge, times(1)).send("produceGameEnded-in-0", game.getId());
        verify(gameRepository, times(1)).save(result);
    }
}
