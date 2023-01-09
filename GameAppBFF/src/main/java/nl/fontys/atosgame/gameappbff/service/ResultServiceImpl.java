package nl.fontys.atosgame.gameappbff.service;

import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.gameappbff.model.FinalResult;
import nl.fontys.atosgame.gameappbff.model.PlayerRoundResult;
import nl.fontys.atosgame.gameappbff.repository.FinalResultRepository;
import nl.fontys.atosgame.gameappbff.repository.PlayerRoundResultRepository;
import nl.fontys.atosgame.gameappbff.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service responsible for saving and returning results
 *
 * @author Niek
 */

@Service
public class ResultServiceImpl implements ResultService {

    private PlayerRoundResultRepository playerRoundResultRepository;
    private FinalResultRepository finalResultRepository;

    private ResultRepository resultRepository;

    /**
     * Create a new ResultServiceImpl
     *
     * @param playerRoundResultRepository The repository for playerRoundResults
     * @param finalResultRepository       The repository for finalResults
     * @param resultRepository            The repository for results
     */
    public ResultServiceImpl(
        @Autowired PlayerRoundResultRepository playerRoundResultRepository,
        @Autowired FinalResultRepository finalResultRepository,
        @Autowired ResultRepository resultRepository
    ) {
        this.playerRoundResultRepository = playerRoundResultRepository;
        this.finalResultRepository = finalResultRepository;
        this.resultRepository = resultRepository;
    }

    /**
     * Get a playerRoundResult
     *
     * @param roundId The id of the round
     * @param playerId The id of the player
     * @return PlayerRoundResult The playerRoundResult to return
     */
    @Override
    public Optional<PlayerRoundResult> getPlayerRoundResult(UUID roundId, UUID playerId) {
        Optional<PlayerRoundResult> playerRoundResult = playerRoundResultRepository.findByRoundIdAndPlayerId(
            roundId,
            playerId
        );
        return playerRoundResult;
    }

    /**
     * Save a playerRoundResult
     *
     * @param playerRoundResult The playerRoundResult to save
     * @return The saved playerRoundResult
     */
    @Override
    public void handlePlayerResultDetermined(PlayerRoundResult playerRoundResult) {
        System.out.println(playerRoundResult.toString());
        playerRoundResultRepository.save(playerRoundResult);
    }

    /**
     * Save a finalResult
     *
     * @param finalResult The finalResult to save
     * @return The saved finalResult
     */
    @Override
    public void handleFinalResultDetermined(FinalResult finalResult) {
        finalResultRepository.save(finalResult);
    }
}
