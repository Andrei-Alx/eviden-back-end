package nl.fontys.atosgame.gameservice.service;

import nl.fontys.atosgame.gameservice.model.CardSet;
import nl.fontys.atosgame.gameservice.model.Game;
import nl.fontys.atosgame.gameservice.model.LobbySettings;
import nl.fontys.atosgame.gameservice.model.RoundSettings;
import nl.fontys.atosgame.gameservice.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    private final CardSetService cardSetService;

    public GameServiceImpl(
            @Autowired GameRepository gameRepository,
            @Autowired CardSetService cardSetService) {
        this.gameRepository = gameRepository;
        this.cardSetService = cardSetService;
    }

    /**
     * Creates a new game
     *
     * @param companyType   The type of company
     * @param lobbySettings The settings for the lobby
     * @param roundSettings The settings for the rounds
     * @return The created game
     */
    @Override
    public Game createGame(String companyType, LobbySettings lobbySettings, List<RoundSettings> roundSettings) {
        // Check if all cardsets exist
        for (RoundSettings roundSetting : roundSettings) {
            cardSetService.getCardSet(roundSetting.getCardSetId()).orElseThrow(() -> new IllegalArgumentException("CardSet not found"));
        }
        // Create game
        Game game = new Game();
        game.setCompanyType(companyType);
        game = gameRepository.save(game);
        // TODO Produce event
        return game;
    }
}
