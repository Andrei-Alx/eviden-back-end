package nl.fontys.atosgame.gameappbff.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.gameappbff.model.FinalResult;
import nl.fontys.atosgame.gameappbff.model.PlayerRoundResult;

public interface ResultService {
    /**
     * Get a player round result by id
     */
    Optional<PlayerRoundResult> getPlayerRoundResult(UUID roundId, UUID playerId);

    /**
     * All player round results for a round
     */
    Optional<List<PlayerRoundResult>> getPlayerRoundResults(UUID roundId);

    /**
     * Handle PlayerResultDetermined event
     */
    void handlePlayerResultDetermined(PlayerRoundResult playerRoundResult);

    /**
     * Handle FinalResultDetermined event
     */
    void handleFinalResultDetermined(FinalResult finalResult);
}
