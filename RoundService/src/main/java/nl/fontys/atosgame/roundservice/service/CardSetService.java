package nl.fontys.atosgame.roundservice.service;

import java.util.*;
import nl.fontys.atosgame.roundservice.model.CardSet;
import nl.fontys.atosgame.roundservice.model.Tag;

public interface CardSetService {
    /**
     * Create a new card set
     * @param cardSet The card set to create
     */
    void createCardSet(CardSet cardSet);

    /**
     * Update an existing card set
     * @param cardSet The card set to update
     */
    void updateCardSet(CardSet cardSet);

    /**
     * Delete an existing card set
     * @param uuid The id of the card set to delete
     */
    void deleteCardSet(java.util.UUID uuid);

    /**
     * Get a card set by id
     * @param uuid The id of the card set to get
     * @return The card set
     */
    Optional<CardSet> getCardSet(UUID uuid);

    /**
     * Get a card set by CardSetType
     * @return the card set
     */
    CardSet getCardSetByTags(Set<Tag> tags);

    /**
     * Get all card sets
     * @return The card sets
     */
    List<CardSet> getAllCardSets();
}
