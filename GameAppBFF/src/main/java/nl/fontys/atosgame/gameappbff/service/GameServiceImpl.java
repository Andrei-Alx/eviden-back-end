package nl.fontys.atosgame.gameappbff.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.EntityNotFoundException;
import nl.fontys.atosgame.gameappbff.controller.GameSocketController;
import nl.fontys.atosgame.gameappbff.enums.GameStatus;
import nl.fontys.atosgame.gameappbff.model.Game;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.model.Round;
import nl.fontys.atosgame.gameappbff.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for handling games.
 * @author Aniek
 */
@Service
public class GameServiceImpl implements GameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);
    private final GameRepository gameRepository;
    private final GameSocketController gameSocketController;

    public GameServiceImpl(
        @Autowired GameRepository gameRepository,
        @Autowired GameSocketController gameSocketController
    ) {
        this.gameRepository = gameRepository;
        this.gameSocketController = gameSocketController;
    }

    /**
     * Create a new game in the database.
     *
     * @param gameId, title The game to create.
     */
    @Override
    public Game handleGameCreated(UUID gameId, String title, String companyType) {
        Game game = new Game(
            gameId,
            title,
            null,
            companyType,
            GameStatus.CREATED,
            new ArrayList<>()
        );
        LOGGER.info(String.format("Game created event recieved in gameappbff => %s", game));
        gameRepository.save(game);
        return game;
    }

    /**
     * start a game in the database by updating the status.
     *
     * @param gameId The game to start.
     */
    @Override
    public Game handleGameStarted(UUID gameId) {
        Optional<Game> game = gameRepository.findById(gameId);
        if (game.isPresent()) {
            Game game1 = game.get();
            game1.setStatus(GameStatus.STARTED);
            gameRepository.save(game1);
            gameSocketController.gameStarted(gameId);
            return game1;
        } else {
            throw new EntityNotFoundException("Game not found");
        }
    }

    /**
     * end a game in the database by updating the status.
     *
     * @param gameId, status The game to end.
     */
    @Override
    public Game handleGameEnded(UUID gameId) {
        Optional<Game> game = gameRepository.findById(gameId);
        if (game.isPresent()) {
            Game game1 = game.get();
            game1.setStatus(GameStatus.ENDED);
            gameRepository.save(game1);
            return game1;
        } else {
            throw new EntityNotFoundException("Game not found");
        }
    }

    /**
     * Add a lobby to a game in the database.
     *
     * @param gameId The game to add the lobby to.
     * @param lobby  The lobby to add.
     * @return The game with the added lobby.
     */
    @Override
    public Game addLobbyToGame(UUID gameId, Lobby lobby) {
        Optional<Game> game = gameRepository.findById(gameId);
        if (game.isPresent()) {
            Game game1 = game.get();
            game1.setLobby(lobby);
            gameRepository.save(game1);
            return game1;
        } else {
            throw new EntityNotFoundException("Game not found");
        }
    }

    /**
     * Add a round to a game in the database.
     *
     * @param round  The round to add.
     * @param gameId The game to add the round to.
     * @return The game with the added round.
     */
    @Override
    public Game addRoundToGame(Round round, UUID gameId) {
        Optional<Game> game = gameRepository.findById(gameId);
        if (game.isPresent()) {
            Game game1 = game.get();
            game1.getRounds().add(round);
            gameRepository.save(game1);
            return game1;
        } else {
            throw new EntityNotFoundException("Game not found");
        }
    }

    /**
     * Get all games from the database.
     *
     * @return List of games.
     */
    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public List<Game> getGamesByStatus() {
        return gameRepository.findByGameStatus();
    }

    /**
     * Get a game from the database.
     *
     * @param gameId The id of the game to get.
     * @return The game.
     */
    @Override
    public Optional<Game> getGame(UUID gameId) {
        return gameRepository.findById(gameId);
    }
}
