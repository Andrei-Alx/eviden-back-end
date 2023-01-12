package nl.fontys.atosgame.roundservice.repository;

import java.util.UUID;
import nl.fontys.atosgame.roundservice.enums.CardSetType;
import nl.fontys.atosgame.roundservice.model.CardSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for cardset related operations
 * @author Eli
 */
@Repository
public interface CardSetRepository extends JpaRepository<CardSet, UUID> {
    /**
     * Get a card set by CardSetType
     */
    CardSet findByTypeAndImportantTag(CardSetType type, String importantTag);
}
