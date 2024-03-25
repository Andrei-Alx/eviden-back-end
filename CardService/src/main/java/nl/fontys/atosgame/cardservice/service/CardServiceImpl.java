package nl.fontys.atosgame.cardservice.service;

import java.util.Collection;
import java.util.UUID;

import nl.fontys.atosgame.cardservice.model.Card;
import nl.fontys.atosgame.cardservice.repository.CardRepository;
import nl.fontys.atosgame.cardservice.service.interfaces.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that handles all card requests
 * calls the repository to store the card in the database
 * and sends a message to the event bus
 * @author Eli
 */
@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    public CardServiceImpl(
        @Autowired CardRepository cardRepository
    ) {
        this.cardRepository = cardRepository;
    }

    /**
     * Create a new card
     * @param CreateCardDto the card to create
     * @return the created card
     */
    @Override
    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    /**
     * Update an existing card
     * @param card the card to update
     * @return the updated card
     */
    @Override
    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }

    /**
     * Delete an existing card
     * @param id the id of the card to delete
     */
    @Override
    public void deleteCard(UUID id) {
        cardRepository.deleteById(id);
    }

    /**
     * Get collection of cards by their ids
     * @param ids the ids of the cards to get
     * @return the collection of cards
     */
    @Override
    public Collection<Card> getCardsByIds(Collection<UUID> ids) {
        return cardRepository.findAllById(ids);
    }

    @Override
    public void deleteCards(Collection<UUID> ids) {
        cardRepository.deleteAllById(ids);
    }
}
