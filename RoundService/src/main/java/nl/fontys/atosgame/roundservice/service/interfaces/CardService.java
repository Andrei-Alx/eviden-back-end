package nl.fontys.atosgame.roundservice.service.interfaces;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.model.Card;

public interface CardService {
    /**
     * Create a new card
     * @param card The card to create
     */
    void createCard(Card card);
    /**
     * Update an existing card
     * @param card The card to update
     */
    void updateCard(Card card);

    /**
     * Delete an existing card
     * @param uuid The id of the card to delete
     */
    void deleteCard(UUID uuid);

    /**
     * Get a card by id
     * @param id The id of the card to get
     * @return Optional of the card
     */
    Optional<Card> getCard(UUID id);

    /**
     * Get multiple cards by ids
     * @param ids The ids of the cards to get
     */
    Collection<Card> getCards(List<UUID> ids);
}
