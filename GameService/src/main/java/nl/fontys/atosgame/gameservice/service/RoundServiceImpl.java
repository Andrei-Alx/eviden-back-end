package nl.fontys.atosgame.gameservice.service;

import nl.fontys.atosgame.gameservice.applicationEvents.RoundEndedAppEvent;
import nl.fontys.atosgame.gameservice.enums.RoundStatus;
import nl.fontys.atosgame.gameservice.model.Round;
import nl.fontys.atosgame.gameservice.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoundServiceImpl implements RoundService {

    private final RoundRepository roundRepository;
    private final GameService gameService;
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    public RoundServiceImpl(RoundRepository roundRepository, GameService gameService, ApplicationEventPublisher eventPublisher) {
        this.roundRepository = roundRepository;
        this.gameService = gameService;
        this.eventPublisher = eventPublisher;
    }

    /**
     * Start a round
     *
     * @param roundId The id of the round
     */
    @Override
    public Round startRound(UUID roundId) {
        Round round = roundRepository.findById(roundId).get();
        round.setStatus(RoundStatus.IN_PROGRESS);
        return roundRepository.save(round);
    }

    /**
     * End a round
     *
     * @param roundId The id of the round
     */
    @Override
    public Round endRound(UUID roundId) {
        Round round = roundRepository.findById(roundId).get();
        round.setStatus(RoundStatus.FINISHED);
        round = roundRepository.save(round);
        eventPublisher.publishEvent(new RoundEndedAppEvent(this, roundId));
        return round;
    }

    /**
     * Create a new round and add it to the game
     *
     * @param gameId The id of the game
     * @param round  The round
     */
    @Override
    public Round createRound(UUID gameId, Round round) {
        // Save round
        Round round1 = roundRepository.save(round);
        // Add round to game
        gameService.addRound(gameId, round);

        return round1;
    }
}
