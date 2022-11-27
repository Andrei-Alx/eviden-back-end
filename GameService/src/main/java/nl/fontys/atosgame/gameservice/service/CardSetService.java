package nl.fontys.atosgame.gameservice.service;

import nl.fontys.atosgame.gameservice.model.CardSet;

import java.util.Optional;
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

    /**
     * Get a cardSet from the database.
     * @param cardSetId The id of the cardSet.
     * @return The cardSet.
     */
    Optional<CardSet> getCardSet(UUID cardSetId);
}
