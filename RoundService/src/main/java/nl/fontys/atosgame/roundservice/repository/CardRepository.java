package nl.fontys.atosgame.roundservice.repository;

import java.util.UUID;
import nl.fontys.atosgame.roundservice.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for card related operations
 * @author Eli
 */
@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {}
