package nl.fontys.atosgame.roundservice.repository;

import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import nl.fontys.atosgame.roundservice.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for round related operations
 * @author Eli
 */
@Repository
public interface RoundRepository extends JpaRepository<Round, UUID> {
    Optional<Round> findByPlayerRoundsContaining(PlayerRound playerRound);
}
