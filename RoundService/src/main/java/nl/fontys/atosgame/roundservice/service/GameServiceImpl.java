package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.model.Game;
import nl.fontys.atosgame.roundservice.model.Lobby;
import nl.fontys.atosgame.roundservice.model.Round;
import nl.fontys.atosgame.roundservice.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service for game related operations
 * @author Eli
 */
@Service
public class GameServiceImpl implements GameService {

    private RoundService roundService;

    private GameRepository gameRepository;

    public GameServiceImpl(@Autowired RoundService roundService, @Autowired GameRepository gameRepository) {
        this.roundService = roundService;
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
        return this.startRound(gameId, 0);
    }

    /**
     * Start a round in a game
     *
     * @param gameId      The id of the game
     * @param roundNumber The number of the round
     * @return The updated game
     * @throws EntityNotFoundException When the game or round is not found
     */
    @Override
    public Game startRound(UUID gameId, int roundNumber) throws EntityNotFoundException {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();

            if (game.getRounds().size() <= roundNumber) {
                throw new EntityNotFoundException("Round not found");
            }

            // Initialize the round
            Round round = game.getRounds().get(roundNumber);
            roundService.initializeRound(round.getId(), game.getLobby().getPlayerIds());

            // Start the round
            round = roundService.startRound(round.getId());
            game.getRounds().set(roundNumber, round);

            return gameRepository.save(game);
        } else {
            throw new EntityNotFoundException("Game not found");
        }
    }
}
