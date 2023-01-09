package nl.fontys.atosgame.roundservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.model.Game;
import nl.fontys.atosgame.roundservice.model.Lobby;
import nl.fontys.atosgame.roundservice.model.Round;
import nl.fontys.atosgame.roundservice.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

/**
 * Service for game related operations
 * @author Eli
 */
@Service
public class GameServiceImpl implements GameService {

    private RoundService roundService;
    private LobbyService lobbyService;

    private GameRepository gameRepository;
    private StreamBridge streamBridge;

    public GameServiceImpl(
        @Autowired RoundService roundService,
        @Autowired LobbyService lobbyService,
        @Autowired GameRepository gameRepository,
        @Autowired StreamBridge streamBridge
    ) {
        this.roundService = roundService;
        this.lobbyService = lobbyService;
        this.gameRepository = gameRepository;
        this.streamBridge = streamBridge;
    }

    /**
     * Initialize a game
     * @param gameId The id of the game
     * @param roundSettings The settings for the rounds
     * @return The initialized game
     */
    @Override
    public Game initializeGame(UUID gameId, List<RoundSettingsDto> roundSettings) {
        Game game = new Game();
        game.setId(gameId);

        game = gameRepository.save(game);

        List<Round> rounds = roundService.createRounds(gameId, roundSettings);
        game.setRounds(rounds);

        // Check if a lobby for the game has been created and add it
        Optional<Lobby> lobby = lobbyService.getLobbyByGameId(gameId);
        if (lobby.isPresent()) {
            game.setLobby(lobby.get());
        }

        return gameRepository.save(game);
    }

    /**
     * Add a lobby to a game
     *
     * @param gameId  The id of the game
     * @param lobby The lobby to add
     * @return The updated game
     */
    @Override
    public Game addLobbyToGame(UUID gameId, Lobby lobby) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();
            game.setLobby(lobby);
            return gameRepository.save(game);
        } else {
            throw new IllegalArgumentException("Game not found");
        }
    }

    /**
     * Start the game
     * Start the first round
     *
     * @param gameId The id of the game
     * @return The updated game
     */
    @Override
    public Game startGame(UUID gameId) throws EntityNotFoundException {
        Game game = gameRepository
            .findById(gameId)
            .orElseThrow(EntityNotFoundException::new);
        Round firstRound = game.getRounds().get(0);
        firstRound =
            roundService.startRound(
                firstRound.getId(),
                game.getLobby().getPlayerIds(),
                gameId
            );
        game.getRounds().set(0, firstRound);
        return gameRepository.save(game);
    }

    /**
     * Check if the next round should be started
     *
     * @param gameId The id of the game
     */
    @Override
    public void checkForNextRound(UUID gameId) {
        // Get game
        Game game = gameRepository
            .findById(gameId)
            .orElseThrow(EntityNotFoundException::new);

        // Get current round
        Optional<Round> currentRound = game.getCurrentRound();
        // If there is no current round, the game is done
        if (currentRound.isEmpty()) {
            throw new IllegalStateException(
                "Game is already done, but checkForNextRound was called. This means the game appears to be done, but some logic still happened."
            );
        }

        // If the current round is done, start the next round
        Round round = currentRound.get();
        if (round.isDone()) {
            // End the current round
            roundService.endRound(round.getId(), gameId);
            // Check if there is a next round
            Optional<Round> nextRound = game.getNextRound();
            if (nextRound.isPresent()) {
                // Start the next round
                roundService.startRound(
                    nextRound.get().getId(),
                    game.getLobby().getPlayerIds(),
                    gameId
                );
            } else {
                // Game is done
                // TODO: Publish event, change status?

            }
            gameRepository.save(game);
        }
    }

    /**
     * Get the game of a round
     *
     * @param roundId The id of the round
     * @return An optional containing the game if found
     */
    @Override
    public Optional<Game> getGameByRoundId(UUID roundId) {
        return gameRepository.getGameByRoundsId(roundId);
    }
}
