package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.model.Game;
import nl.fontys.atosgame.roundservice.model.Round;
import nl.fontys.atosgame.roundservice.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        game.setAmountOfPlayers(0);

        game = gameRepository.save(game);

        List<Round> rounds = roundService.createRounds(gameId, roundSettings);
        game.setRounds(rounds);

        return gameRepository.save(game);
    }
}
