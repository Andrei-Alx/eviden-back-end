package nl.fontys.atosgame.roundservice.repository;

import java.util.UUID;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for player rounds related operations
 * @author Eli
 */
@Repository
public interface PlayerRoundRepository extends JpaRepository<PlayerRound, UUID> {}
