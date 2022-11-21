package nl.fontys.atosgame.gameservice.service;

import nl.fontys.atosgame.gameservice.dto.CreateGameEventDto;
import nl.fontys.atosgame.gameservice.enums.ShuffleMethod;
import nl.fontys.atosgame.gameservice.model.CardSet;
import nl.fontys.atosgame.gameservice.model.Game;
import nl.fontys.atosgame.gameservice.model.LobbySettings;
import nl.fontys.atosgame.gameservice.model.RoundSettings;
import nl.fontys.atosgame.gameservice.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        String companyType = "companyType";
        LobbySettings lobbySettings = new LobbySettings();
        List<RoundSettings> roundSettings = new ArrayList<>(
                List.of(new RoundSettings(false, 1, 1, ShuffleMethod.FULLY_RANDOM, false, UUID.randomUUID()),
                        new RoundSettings(false, 1, 1, ShuffleMethod.FULLY_RANDOM, false, UUID.randomUUID()))
        );
        when(cardSetService.getCardSet(any())).thenReturn(Optional.of(mock(CardSet.class)));
        when(gameRepository.save(any())).thenAnswer(invocation -> {
            Game game = invocation.getArgument(0);
            game.setId(UUID.randomUUID());
            return game;
        });

        Game result = gameService.createGame("companyType", lobbySettings, roundSettings);

        assertNotNull(result.getId());
        assertEquals(companyType, result.getCompanyType());
        assertNull(result.getLobby());
        assertNull(result.getRounds());
        verify(gameRepository, times(1)).save(any());
        verify(streamBridge, times(1)).send("produceGameCreated-in-0", new CreateGameEventDto(result.getId(), companyType, roundSettings, lobbySettings));
    }

    @Test
    void createGameCardSetsDoNotExist() {
        String companyType = "companyType";
        LobbySettings lobbySettings = new LobbySettings();
        List<RoundSettings> roundSettings = new ArrayList<>(
                List.of(new RoundSettings(false, 1, 1, ShuffleMethod.FULLY_RANDOM, false, UUID.randomUUID()),
                        new RoundSettings(false, 1, 1, ShuffleMethod.FULLY_RANDOM, false, UUID.randomUUID()))
        );
        when(cardSetService.getCardSet(any())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> gameService.createGame("companyType", lobbySettings, roundSettings));
    }
}