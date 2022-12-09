package nl.fontys.atosgame.gameappbff.service;

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
     * Handle PlayerResultDetermined event
     */
    void handlePlayerResultDetermined(PlayerRoundResult playerRoundResult);

    /**
     * Handle FinalResultDetermined event
     */
    void handleFinalResultDetermined(FinalResult finalResult);
}
