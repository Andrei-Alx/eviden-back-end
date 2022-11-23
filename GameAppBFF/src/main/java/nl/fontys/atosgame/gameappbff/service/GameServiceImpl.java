package nl.fontys.atosgame.gameappbff.service;

import java.util.ArrayList;
import java.util.UUID;
import nl.fontys.atosgame.gameappbff.enums.GameStatus;
import nl.fontys.atosgame.gameappbff.model.Game;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.model.Round;
import nl.fontys.atosgame.gameappbff.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for handling games.
 * @author Aniek
 */
@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(@Autowired GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     * Create a new game in the database.
     *
     * @param gameId, title The game to create.
     */
    @Override
    public Game handleGameCreated(UUID gameId, String title) {
        Game game = new Game(gameId, title, null, GameStatus.CREATED, new ArrayList<>());
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
        if (gameRepository.findById(gameId).isPresent()) {
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
        Game game = null;
        if (gameRepository.findById(gameId).isPresent()) {
            game = gameRepository.findById(gameId).get();
            game.setStatus(GameStatus.ENDED);
            gameRepository.save(game);
        }

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
        Game game = null;
        if (gameRepository.findById(gameId).isPresent()) {
            game = gameRepository.findById(gameId).get();
            game.setLobby(lobby);
            gameRepository.save(game);
        }

        return game;
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
        Game game = null;
        if(gameRepository.findById(gameId).isPresent()) {
            game = gameRepository.findById(gameId).get();
            game.getRounds().add(round);
            gameRepository.save(game);
        }

        return game;
    }
}
