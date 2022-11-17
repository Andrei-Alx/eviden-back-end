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

    private GameRepository gameRepository;
    private StreamBridge streamBridge;

    public GameServiceImpl(
        @Autowired RoundService roundService,
        @Autowired GameRepository gameRepository,
        @Autowired StreamBridge streamBridge
    ) {
        this.roundService = roundService;
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

        // TODO: REMOVE HARDCODED LOBBY
        Lobby lobby = new Lobby(
            UUID.randomUUID(),
            new ArrayList<>(
                List.of(
                    UUID.randomUUID(),
                    UUID.randomUUID(),
                    UUID.randomUUID(),
                    UUID.randomUUID()
                )
            )
        );
        game.setLobby(lobby);

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
}
