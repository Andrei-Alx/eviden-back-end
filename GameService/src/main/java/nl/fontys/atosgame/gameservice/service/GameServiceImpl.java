package nl.fontys.atosgame.gameservice.service;

import nl.fontys.atosgame.gameservice.dto.CreateGameEventDto;
import nl.fontys.atosgame.gameservice.enums.GameStatus;
import nl.fontys.atosgame.gameservice.model.CardSet;
import nl.fontys.atosgame.gameservice.model.Game;
import nl.fontys.atosgame.gameservice.model.LobbySettings;
import nl.fontys.atosgame.gameservice.model.RoundSettings;
import nl.fontys.atosgame.gameservice.repository.GameRepository;
import org.apache.kafka.streams.StreamsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    private final CardSetService cardSetService;

    private final StreamBridge streamBridge;

    public GameServiceImpl(
            @Autowired GameRepository gameRepository,
            @Autowired CardSetService cardSetService,
            @Autowired StreamBridge streamBridge) {
        this.gameRepository = gameRepository;
        this.cardSetService = cardSetService;
        this.streamBridge = streamBridge;
    }

    /**
     * Creates a new game
     * @param title The title of the game
     * @param companyType   The type of company
     * @param lobbySettings The settings for the lobby
     * @param roundSettings The settings for the rounds
     * @return The created game
     */
    @Override
    public Game createGame(String title, String companyType, LobbySettings lobbySettings, List<RoundSettings> roundSettings) {
        // Check if all cardsets exist
        for (RoundSettings roundSetting : roundSettings) {
            cardSetService.getCardSet(roundSetting.getCardSetId()).orElseThrow(() -> new IllegalArgumentException("CardSet not found"));
        }
        // Create game
        Game game = new Game();
        game.setCompanyType(companyType);
        game.setTitle(title);
        game.setStatus(GameStatus.CREATED);
        game = gameRepository.save(game);
        // TODO Produce event
        CreateGameEventDto createGameEventDto = new CreateGameEventDto(game.getId(), title, companyType, roundSettings, lobbySettings);
        streamBridge.send("produceGameCreated-in-0", createGameEventDto);
        return game;
    }

    /**
     * Starts a game
     *
     * @param gameId The id of the game
     * @return The started game
     */
    @Override
    public Game startGame(UUID gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Game not found"));
        game.setStatus(GameStatus.STARTED);
        game = gameRepository.save(game);
        streamBridge.send("produceGameStarted-in-0", game.getId());
        return game;
    }
}
