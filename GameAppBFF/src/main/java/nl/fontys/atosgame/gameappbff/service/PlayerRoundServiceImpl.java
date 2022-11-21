package nl.fontys.atosgame.gameappbff.service;

import java.util.ArrayList;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import nl.fontys.atosgame.gameappbff.controller.GameSocketController;
import nl.fontys.atosgame.gameappbff.model.PlayerRound;
import nl.fontys.atosgame.gameappbff.model.Round;
import nl.fontys.atosgame.gameappbff.repository.PlayerRoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that manages playerRounds
 *
 * @author Eli
 */
@Service
public class PlayerRoundServiceImpl implements PlayerRoundService {

    private PlayerRoundRepository playerRoundRepository;
    private RoundService roundService;

    private GameSocketController gameSocketController;

    public PlayerRoundServiceImpl(
        @Autowired PlayerRoundRepository playerRoundRepository,
        @Autowired RoundService roundService,
        @Autowired GameSocketController gameSocketController
    ) {
        this.playerRoundRepository = playerRoundRepository;
        this.roundService = roundService;
        this.gameSocketController = gameSocketController;
    }

    /**
     * Create a playerRound
     *
     * @param playerId The id of the player
     * @param roundId  The id of the round
     * @return The created playerRound
     */
    @Override
    public PlayerRound createPlayerRound(UUID playerId, UUID roundId) {
        PlayerRound playerRound = new PlayerRound(
            null,
            playerId,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        );
        playerRound = playerRoundRepository.save(playerRound);
        roundService.addPlayerRound(roundId, playerRound);
        return playerRound;
    }

    /**
     * Starts a playerRound. Handles creating a playerRound if it doesn't exist yet
     * @param playerId The id of the player
     * @param roundId The id of the round
     * @param gameId The id of the game
     * @param phaseNumber The phase number
     * @return The updated playerRound
     */
    @Override
    public PlayerRound startPhase(
        UUID playerId,
        UUID roundId,
        UUID gameId,
        int phaseNumber
    ) {
        Round round = roundService.getRound(roundId).get();
        // Find the playerRound for the player or create a new one
        PlayerRound playerRound = null;
        for (PlayerRound pr : round.getPlayerRounds()) {
            if (pr.getPlayerId().equals(playerId)) {
                playerRound = pr;
                break;
            }
        }
        if (playerRound == null) {
            playerRound = createPlayerRound(playerId, roundId);
            round.addPlayerRound(playerRound);
        }

        // Set the phase number
        // TODO: Should there be some signifier for the phase here?

        // Send to socket
        gameSocketController.playerPhase(gameId, playerId, phaseNumber);
        return playerRoundRepository.save(playerRound);
    }

    /**
     * Ends a playerRound
     *
     * @param playerId    The id of the player
     * @param roundId     The id of the round
     * @param gameId      The id of the game
     * @param phaseNumber The phase number
     * @return The updated playerRound
     */
    @Override
    public PlayerRound endPhase(
        UUID playerId,
        UUID roundId,
        UUID gameId,
        int phaseNumber
    ) {
        Round round = roundService.getRound(roundId).get();
        // Find the playerRound for the player
        PlayerRound playerRound = round
            .getPlayerRounds()
            .stream()
            .filter(pr -> pr.getPlayerId().equals(playerId))
            .findFirst()
            .orElseThrow(EntityNotFoundException::new);

        // Set the phase number
        // TODO: Should there be some signifier for the phase here?

        //        // Send to socket
        //        gameSocketController.playerPhase(gameId, playerId, phaseNumber);
        return playerRoundRepository.save(playerRound);
    }
}
