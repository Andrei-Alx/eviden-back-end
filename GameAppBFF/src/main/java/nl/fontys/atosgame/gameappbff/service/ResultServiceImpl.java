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

@Service
public class ResultServiceImpl implements ResultService {

    private PlayerRoundResultRepository playerRoundResultRepository;
    private FinalResultRepository finalResultRepository;

    private ResultRepository resultRepository;

    public ResultServiceImpl(
        @Autowired PlayerRoundResultRepository playerRoundResultRepository,
        @Autowired FinalResultRepository finalResultRepository,
        @Autowired ResultRepository resultRepository) {
        this.playerRoundResultRepository = playerRoundResultRepository;
        this.finalResultRepository = finalResultRepository;
        this.resultRepository = resultRepository;
    }

    /**
     * Get a player round result by round id and player id
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
     * Handle PlayerResultDetermined event
     * save player round result
     */
    @Override
    public void handlePlayerResultDetermined(PlayerRoundResult playerRoundResult) {
        resultRepository.save(playerRoundResult.getResult());
        playerRoundResultRepository.save(playerRoundResult);
    }

    /**
     * Handle FinalResultDetermined event
     * save final result
     */
    @Override
    public void handleFinalResultDetermined(FinalResult finalResult) {
        // I might have to add this to prevent referential integrity errors
        // resultRepository.save(finalResult.getResults());
        finalResultRepository.save(finalResult);
    }
}
