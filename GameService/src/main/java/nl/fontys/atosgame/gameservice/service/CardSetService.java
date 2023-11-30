package nl.fontys.atosgame.gameservice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.gameservice.model.CardSet;

/**
 * Service for handling cardsets.
 * @author Aniek
 */
public interface CardSetService {
    /**
     * Create a new cardset in the database.
     * @param cardSet The cardset to create.
     */
    void createCardSet(CardSet cardSet);

    /**
     * Update a cardSet in the database.
     * @param cardSet The card to update.
     */
    void updateCardSet(CardSet cardSet);

    /**
     * Delete a cardSet from the database.
     * @param cardSetId The id of the card to delete.
     */
    void deleteCardSet(UUID cardSetId);

    /**
     * Get a cardSet from the database.
     * @param cardSetId The id of the cardSet.
     * @return The cardSet.
     */
    Optional<CardSet> getCardSet(UUID cardSetId);

    List<CardSet> getAllCardSets();

    void cardSetRequest();
}
