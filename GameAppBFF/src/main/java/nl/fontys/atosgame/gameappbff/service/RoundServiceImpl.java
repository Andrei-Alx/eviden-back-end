package nl.fontys.atosgame.gameappbff.service;

import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.gameappbff.controller.GameSocketController;
import nl.fontys.atosgame.gameappbff.enums.RoundStatus;
import nl.fontys.atosgame.gameappbff.model.PlayerRound;
import nl.fontys.atosgame.gameappbff.model.Round;
import nl.fontys.atosgame.gameappbff.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that manages the rounds
 * @author Eli
 */
@Service
public class RoundServiceImpl implements RoundService {

    private GameService gameService;
    private RoundRepository roundRepository;
    private GameSocketController gameSocketController;

    public RoundServiceImpl(
        @Autowired GameService gameService,
        @Autowired RoundRepository roundRepository,
        @Autowired GameSocketController gameSocketController
    ) {
        this.gameService = gameService;
        this.roundRepository = roundRepository;
        this.gameSocketController = gameSocketController;
    }

    /**
     * Get a round by id
     *
     * @param id The id of the round
     * @return Optional of the round
     */
    @Override
    public Optional<Round> getRound(UUID id) {
        return roundRepository.findById(id);
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

    /**
     * Updates the round with the given id
     *
     * @param roundId The id of the round to update
     * @param gameId  The id of the game the round belongs to
     */
    @Override
    public Round startRound(UUID roundId, UUID gameId) {
        Round round = roundRepository.findById(roundId).get();
        round.setStatus(RoundStatus.IN_PROGRESS);
        roundRepository.save(round);
        gameSocketController.roundStarted(gameId, roundId);
        return round;
    }

    /**
     * Marks a round as ended and sends an event over the websocket
     *
     * @param roundId The id of the round to update
     * @param gameId  The id of the game the round belongs to
     * @return The updated round
     */
    @Override
    public Round endRound(UUID roundId, UUID gameId) {
        Round round = roundRepository.findById(roundId).get();
        round.setStatus(RoundStatus.FINISHED);
        roundRepository.save(round);
        gameSocketController.roundEnded(gameId, roundId);
        return round;
    }

    /**
     * Adds a playerRound to the round
     *
     * @param roundId     The id of the round
     * @param playerRound The playerRound to add
     * @return The updated round
     */
    @Override
    public Round addPlayerRound(UUID roundId, PlayerRound playerRound) {
        Round round = roundRepository.findById(roundId).get();
        round.addPlayerRound(playerRound);
        roundRepository.save(round);
        return round;
    }
}
