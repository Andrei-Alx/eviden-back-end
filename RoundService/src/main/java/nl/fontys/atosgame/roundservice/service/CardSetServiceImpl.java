package nl.fontys.atosgame.roundservice.service;

import java.util.Optional;
import java.util.UUID;

import nl.fontys.atosgame.roundservice.enums.CardSetType;
import nl.fontys.atosgame.roundservice.model.CardSet;
import nl.fontys.atosgame.roundservice.repository.CardSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for card set related operations
 * @author Eli
 */
@Service
public class CardSetServiceImpl implements CardSetService {

    private CardSetRepository cardSetRepository;

    public CardSetServiceImpl(@Autowired CardSetRepository cardSetRepository) {
        this.cardSetRepository = cardSetRepository;
    }

    /**
     * Create a new card set
     * @param cardSet The card set to create
     */
    @Override
    public void createCardSet(CardSet cardSet) {
        cardSetRepository.save(cardSet);
    }

    /**
     * Update an existing card set
     * @param cardSet The card set to update
     */
    @Override
    public void updateCardSet(CardSet cardSet) {
        cardSetRepository.save(cardSet);
    }

    /**
     * Delete an existing card set
     * @param uuid The id of the card set to delete
     */
    @Override
    public void deleteCardSet(UUID uuid) {
        cardSetRepository.deleteById(uuid);
    }

    /**
     * Get a card set by id
     *
     * @param uuid The id of the card set to get
     * @return The card set
     */
    @Override
    public Optional<CardSet> getCardSet(UUID uuid) {
        return cardSetRepository.findById(uuid);
    }


    /**
     * Get a card set by CardSetType
     * @return the card set
     */
    public CardSet getCardSetByTypeAndImportantTag(CardSetType type, String importantTag) {
        return cardSetRepository.findByTypeAndImportantTag(type, importantTag);
    }
}
