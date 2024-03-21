package nl.fontys.atosgame.gameservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import nl.fontys.atosgame.gameservice.applicationEvents.RoundEndedAppEvent;
import nl.fontys.atosgame.gameservice.dto.CreateGameEventDto;
import nl.fontys.atosgame.gameservice.enums.GameStatus;
import nl.fontys.atosgame.gameservice.enums.RoundStatus;
import nl.fontys.atosgame.gameservice.exceptions.EmptyStringException;
import nl.fontys.atosgame.gameservice.model.*;
import nl.fontys.atosgame.gameservice.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService, ApplicationListener<RoundEndedAppEvent> {

    private final GameRepository gameRepository;

    private final CardSetService cardSetService;

    private final StreamBridge streamBridge;

    public GameServiceImpl(
        @Autowired GameRepository gameRepository,
        @Autowired CardSetService cardSetService,
        @Autowired StreamBridge streamBridge
    ) {
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
    public Game createGame(
        String title,
        String companyType,
        LobbySettings lobbySettings,
        List<RoundSettings> roundSettings
    ) throws EmptyStringException
    {
        List<CardSet> sets = cardSetService.getAllCardSets();
        // Check if all cardsets exist
        for (RoundSettings roundSetting : roundSettings) {
            cardSetService
                .getCardSetById(roundSetting.getCardSetId())
                .orElseThrow(() -> new IllegalArgumentException("CardSet not found"));
        }

        // Check if Null or empty.
        if (title == null || title.trim().isEmpty()) {
            throw new EmptyStringException("title is empty for the lobby");
        }

        // Create game
        Game game = new Game();
        game.setCompanyType(companyType);
        game.setTitle(title);
        game.setStatus(GameStatus.CREATED);
        game = gameRepository.save(game);
        // TODO Produce event
        CreateGameEventDto createGameEventDto = new CreateGameEventDto(
            game.getId(),
            title,
            companyType,
            roundSettings,
            lobbySettings
        );
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
        Game game = gameRepository
            .findById(gameId)
            .orElseThrow(() -> new IllegalArgumentException("Game not found"));
        game.setStatus(GameStatus.STARTED);
        game = gameRepository.save(game);
        streamBridge.send("produceGameStarted-in-0", game.getId());
        return game;
    }

    /**
     * Ends a game
     *
     * @param gameId The id of the game
     * @return The ended game
     */
    @Override
    public Game endGame(UUID gameId) {
        Game game = gameRepository
            .findById(gameId).get();
        game.setStatus(GameStatus.ENDED);
        game = gameRepository.save(game);
        streamBridge.send("produceGameEnded-in-0", game.getId());
        return game;
    }

    /**
     * Add a round to a game
     *
     * @param gameId The id of the game
     * @param round  The round
     * @return The game with the added round
     */
    @Override
    public Game addRound(UUID gameId, Round round) {
        Game game = gameRepository
            .findById(gameId).get();
        List<Round> rounds = game.getRounds();
        if (rounds == null) {
            rounds = new ArrayList<>();
        }
        rounds.add(round);
        game.setRounds(rounds);
        return gameRepository.save(game);
    }

    @Override
    public void onApplicationEvent(RoundEndedAppEvent event) {
        Game game = gameRepository.findByRoundsId(event.getRoundId()).get();
        if (gameIsDone(game)) {
            endGame(game.getId());
        }
    }

    @Override
    public boolean gameIsDone(Game game) {
        return game.getRounds().stream().allMatch((round -> round.getStatus() == (RoundStatus.FINISHED)));
    }
}
