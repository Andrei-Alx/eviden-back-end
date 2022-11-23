package nl.fontys.atosgame.roundservice.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.model.Card;
import nl.fontys.atosgame.roundservice.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for card related operations
 * @author Eli
 */
@Service
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    public CardServiceImpl(@Autowired CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * Create a new card
     * @param card The card to create
     */
    @Override
    public void createCard(Card card) {
        cardRepository.save(card);
    }

    /**
     *  Update an existing card
     * @param card The card to update
     */
    @Override
    public void updateCard(Card card) {
        cardRepository.save(card);
    }

    /**
     * Delete an existing card
     * @param uuid The id of the card to delete
     */
    @Override
    public void deleteCard(UUID uuid) {
        cardRepository.deleteById(uuid);
    }

    /**
     * Get a card by id
     *
     * @param id The id of the card to get
     * @return Optional of the card
     */
    @Override
    public Optional<Card> getCard(UUID id) {
        return cardRepository.findById(id);
    }

    /**
     * Get multiple cards by ids
     *
     * @param ids The ids of the cards to get
     */
    @Override
    public Collection<Card> getCards(List<UUID> ids) {
        return cardRepository.findAllById(ids);
    }
}
