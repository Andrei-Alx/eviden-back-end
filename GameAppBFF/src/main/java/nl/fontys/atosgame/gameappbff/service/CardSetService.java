package nl.fontys.atosgame.gameappbff.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.gameappbff.enums.CardSetType;
import nl.fontys.atosgame.gameappbff.model.CardSet;

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
     * Get cardSets from the database by type.
     * @param cardSetType The type of the card to get.
     * @return A list of cardSets.
     */
    Optional<ArrayList<CardSet>> getCardSetsByType(CardSetType cardSetType);
}
