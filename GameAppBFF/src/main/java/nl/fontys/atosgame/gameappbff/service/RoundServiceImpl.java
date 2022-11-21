package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.model.Round;
import nl.fontys.atosgame.gameappbff.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.RoundRectangle2D;
import java.util.UUID;

/**
 * Service that manages the rounds
 * @author Eli
 */
@Service
public class RoundServiceImpl implements RoundService {

    private GameService gameService;
    private RoundRepository roundRepository;

    public RoundServiceImpl(
            @Autowired GameService gameService,
            @Autowired RoundRepository roundRepository) {
        this.gameService = gameService;
        this.roundRepository = roundRepository;
    }

    /**
     * Creates a new round for the given game
     *
     * @param round  The round to create
     * @param gameId The id of the game to create the round for
     */
    @Override
    public void handleRoundCreatedEvent(Round round, UUID gameId) {
        roundRepository.save(round);
        gameService.addRoundToGame(round, gameId);
    }
}
