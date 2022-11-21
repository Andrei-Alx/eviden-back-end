package nl.fontys.atosgame.roundservice.repository;

import java.util.UUID;
import nl.fontys.atosgame.roundservice.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< Updated upstream
=======
import java.util.UUID;

/**
 * Repository for round related operations
 * @author Eli
 */
>>>>>>> Stashed changes
@Repository
public interface RoundRepository extends JpaRepository<Round, UUID> {}
