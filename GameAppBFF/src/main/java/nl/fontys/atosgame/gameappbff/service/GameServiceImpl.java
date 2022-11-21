package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.enums.GameStatus;
import nl.fontys.atosgame.gameappbff.model.Game;
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

    public GameServiceImpl(@Autowired GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     * Create a new game in the database.
     *
     * @param gameId The game to create.
     */
    @Override
    public Game handleGameCreated(UUID gameId) {
        Game game = new Game(gameId, GameStatus.CREATED);
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
}
