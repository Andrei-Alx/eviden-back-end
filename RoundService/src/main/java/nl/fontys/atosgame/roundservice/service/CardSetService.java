package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.model.CardSet;

import java.util.Optional;
import java.util.UUID;

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
}
