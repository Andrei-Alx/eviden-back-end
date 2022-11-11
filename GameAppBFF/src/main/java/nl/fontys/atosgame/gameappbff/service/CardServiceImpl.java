package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.model.Card;
import nl.fontys.atosgame.gameappbff.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service for handling cards.
 * @author Eli
 */
@Service
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    public CardServiceImpl(@Autowired CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * Create a new card in the database.
     *
     * @param card The card to create.
     */
    @Override
    public void handleCardCreated(Card card) {
        cardRepository.save(card);
    }

    /**
     * Update a card in the database.
     *
     * @param card The card to update.
     */
    @Override
    public void handleCardUpdated(Card card) {

    }

    /**
     * Delete a card from the database.
     *
     * @param cardId The id of the card to delete.
     */
    @Override
    public void handleCardDeleted(UUID cardId) {

    }
}
