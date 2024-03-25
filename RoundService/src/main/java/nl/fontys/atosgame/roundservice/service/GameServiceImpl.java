package nl.fontys.atosgame.roundservice.service;

import java.util.*;

import jakarta.persistence.EntityNotFoundException;
import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.enums.RoundStatus;
import nl.fontys.atosgame.roundservice.enums.ShowResults;
import nl.fontys.atosgame.roundservice.model.Game;
import nl.fontys.atosgame.roundservice.model.Lobby;
import nl.fontys.atosgame.roundservice.model.Round;
import nl.fontys.atosgame.roundservice.repository.GameRepository;
import nl.fontys.atosgame.roundservice.service.interfaces.GameService;
import nl.fontys.atosgame.roundservice.service.interfaces.LobbyService;
import nl.fontys.atosgame.roundservice.service.interfaces.RoundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for game related operations
 * @author Eli
 */
@Service
public class GameServiceImpl implements GameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);
    private final RoundService roundService;
    private final LobbyService lobbyService;
    private final GameRepository gameRepository;

    public GameServiceImpl(
        @Autowired RoundService roundService,
        @Autowired LobbyService lobbyService,
        @Autowired GameRepository gameRepository
    ) {
        this.roundService = roundService;
        this.lobbyService = lobbyService;
        this.gameRepository = gameRepository;
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

        for (Round round : game.getRounds()) {
            if (round.getRoundSettings().getShowPersonalOrGroupResults() == ShowResults.PERSONAL) {
                int indexOfFirstRound = 0;

                // Find the index of the first round with the same ShowResults
                for (int i = 0; i < game.getRounds().size(); i++) {
                    if (game.getRounds().get(i).getRoundSettings().getShowPersonalOrGroupResults() == ShowResults.PERSONAL) {
                        indexOfFirstRound = i;
                        break;
                    }
                }
                // Swap the current round with the first round
                Collections.swap(game.getRounds(), indexOfFirstRound, 0);
                break;
            }
        }
        LOGGER.info(String.format("game starting event (GameEventConsumers class)=> %s", game.getRounds().get(0)));
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

        // Get all rounds in the game
        List<Round> rounds = game.getRounds();
        // get the latest round with status FINISHED
        Round[] latestFinishedRound = new Round[2];
        Round[] possibleNextRound = new Round[2];
        int x = 0;
        int y = 0;
        for (Round round : rounds) {

            if (round.getStatus() == RoundStatus.FINISHED) {
                latestFinishedRound[x] = round;
                x++;
            }
            if (round.getStatus() == RoundStatus.CREATED){
                possibleNextRound[y] = round;
                y++;
            }
        }
        Round nextRound = null;
        for(Round round : possibleNextRound)
        {
            if(round == null)
            {

            }
            else if ("roundTwoCards".equals(round.getRoundSettings().getCardSet().getName()))
            {
                nextRound = round;
                break;
            }
            else{
                nextRound = round;
            }
        }

        // If there is no next round, the game is done
        if (possibleNextRound == null) {
            throw new IllegalStateException(
                "Game is already done, but checkForNextRound was called. This means the game appears to be done, but some logic still happened."
            );
        }

        // If the current round is done, start the next round

            // Check if there is a next round

                // Start the next round
                roundService.startRound(
                    nextRound.getId(),
                    game.getLobby().getPlayerIds(),
                    gameId
                );

            gameRepository.save(game);
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

    /**
     * Get the next round
     * @return An optional containing the next round or an empty optional if there is no more rounds
     */
    @Override
    public Optional<Round> getNextRound(Game game) {
        for (Round round : game.getRounds()) {
            if (round.getStatus() == RoundStatus.CREATED) {
                return Optional.of(round);
            }
        }
        return Optional.empty();
    }
}
