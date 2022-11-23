package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.controller.GameSocketController;
import nl.fontys.atosgame.gameappbff.enums.GameStatus;
import nl.fontys.atosgame.gameappbff.model.Game;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

/**
 * Service for handling games.
 * @author Aniek
 */
@Service
public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;
    private GameSocketController gameSocketController;

    public GameServiceImpl(@Autowired GameRepository gameRepository, @Autowired GameSocketController gameSocketController) {
        this.gameRepository = gameRepository;
        this.gameSocketController = gameSocketController;
    }

    /**
     * Create a new game in the database.
     *
     * @param gameId The game to create.
     */
    @Override
    public Game handleGameCreated(UUID gameId) {
        Game game = new Game(gameId, null, GameStatus.CREATED);
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
        if(game.isPresent()) {
            Game game1 = game.get();
            game1.setStatus(GameStatus.STARTED);
            gameRepository.save(game1);
            return game1;
        }
        else {
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
        if(game.isPresent()) {
            Game game1 = game.get();
            game1.setStatus(GameStatus.ENDED);
            gameRepository.save(game1);
            return game1;
        }
        else {
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
        if(game.isPresent()) {
            Game game1 = game.get();
            game1.setLobby(lobby);
            gameRepository.save(game1);
            return game1;
        }
        else {
            throw new EntityNotFoundException("Game not found");
        }
    }
}
