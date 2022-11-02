package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.model.CardSet;

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
}
