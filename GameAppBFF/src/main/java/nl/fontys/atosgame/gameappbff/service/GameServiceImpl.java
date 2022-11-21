package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.controller.GameSocketController;
import nl.fontys.atosgame.gameappbff.enums.GameStatus;
import nl.fontys.atosgame.gameappbff.model.Game;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service for handling games.
 * @author Aniek
 */
@Service
public class GameServiceImpl implements GameService{

    private GameRepository gameRepository;
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
        Game game = null;
        if(gameRepository.findById(gameId).isPresent()) {
            game = gameRepository.findById(gameId).get();
            game.setStatus(GameStatus.STARTED);
            gameRepository.save(game);
        }

        return game;
    }

    /**
     * end a game in the database by updating the status.
     *
     * @param gameId, status The game to end.
     */
    @Override
    public Game handleGameEnded(UUID gameId) {
        Game game = gameRepository.findById(gameId).get();
        game.setStatus(GameStatus.ENDED);
        gameRepository.save(game);
        return game;
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
        Game game = gameRepository.findById(gameId).get();
        game.setLobby(lobby);
        return gameRepository.save(game);
    }
}
