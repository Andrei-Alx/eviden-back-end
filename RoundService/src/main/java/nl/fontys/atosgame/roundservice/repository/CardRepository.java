package nl.fontys.atosgame.roundservice.repository;

import java.util.UUID;
import nl.fontys.atosgame.roundservice.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< Updated upstream
=======
import java.util.UUID;

/**
 * Repository for card related operations
 * @author Eli
 */
>>>>>>> Stashed changes
@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {}
