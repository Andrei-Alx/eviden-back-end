package nl.fontys.atosgame.gameappbff.service;

import java.util.UUID;
import nl.fontys.atosgame.gameappbff.model.Card;

/**
 * Service for handling cards.
 * @author Eli
 */
public interface CardService {
    /**
     * Create a new card in the database.
     * @param card The card to create.
     */
    void handleCardCreated(Card card);

    /**
     * Update a card in the database.
     * @param card The card to update.
     */
    void handleCardUpdated(Card card);

    /**
     * Delete a card from the database.
     * @param cardId The id of the card to delete.
     */
    void handleCardDeleted(UUID cardId);
}
