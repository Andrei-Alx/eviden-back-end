package nl.fontys.atosgame.gameappbff.service;

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
    public void handleGameCreated(UUID gameId) {
        gameRepository.save(gameId);
    }

    /**
     * start a game in the database.
     *
     * @param gameId The game to start.
     */
    @Override
    public void handleGameStarted(UUID gameId) {
        // todo start game and start round by starting playerrounds

    }
}
