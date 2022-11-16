package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.model.CardSet;

import java.util.UUID;

/**
 * Service for handling cardsets.
 * @author Aniek
 */
public interface CardSetService {
    /**
     * Create a new cardset in the database.
     * @param cardSet The cardset to create.
     */
    void handleCardSetCreated(CardSet cardSet);

    /**
     * Update a cardSet in the database.
     * @param cardSet The card to update.
     */
    void handleCardSetUpdated(CardSet cardSet);

    /**
     * Delete a cardSet from the database.
     * @param cardSetId The id of the card to delete.
     */
    void handleCardSetDeleted(UUID cardSetId);
}
