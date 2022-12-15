package nl.fontys.atosgame.gameappbff.repository;

import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.gameappbff.model.PlayerRoundResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRoundResultRepository
    extends JpaRepository<PlayerRoundResult, UUID> {
    Optional<PlayerRoundResult> findByRoundIdAndPlayerId(UUID roundId, UUID playerId);
}
