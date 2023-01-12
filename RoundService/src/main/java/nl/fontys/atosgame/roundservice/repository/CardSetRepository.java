package nl.fontys.atosgame.roundservice.repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import nl.fontys.atosgame.roundservice.model.CardSet;
import nl.fontys.atosgame.roundservice.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for cardset related operations
 * @author Eli
 */
@Repository
public interface CardSetRepository extends JpaRepository<CardSet, UUID> {
    /**
     * Get a card set by tags
     */
    CardSet findCardSetByTagsIn(Set<Tag> tags);
}
