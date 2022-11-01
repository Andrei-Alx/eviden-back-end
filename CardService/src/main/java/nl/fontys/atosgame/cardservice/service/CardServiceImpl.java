package nl.fontys.atosgame.cardservice.service;

import nl.fontys.atosgame.cardservice.dto.CreateCardDto;
import nl.fontys.atosgame.cardservice.model.Card;
import nl.fontys.atosgame.cardservice.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

/**
 * Service that handles all card requests
 * calls the repository to store the card in the database
 * and sends a message to the event bus
 * @author Eli
 */
@Service
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;
    private StreamBridge streamBridge;

    public CardServiceImpl(@Autowired CardRepository cardRepository, @Autowired StreamBridge streamBridge) {
        this.cardRepository = cardRepository;
        this.streamBridge = streamBridge;
    }

    /**
     * Create a new card
     * @param createCardDto the card to create
     * @return the created card
     */
    @Override
    public Card createCard(CreateCardDto createCardDto) {
        Card card = cardRepository.save(new Card(null, createCardDto.getTags(), createCardDto.getTranslations()));
        streamBridge.send("cardCreated-in-0", card);
        return card;
    }

    /**
     * Update an existing card
     * @param card the card to update
     * @return the updated card
     */
    @Override
    public Card updateCard(Card card) {
        Card updatedCard = cardRepository.save(card);
        streamBridge.send("cardUpdated-in-0", updatedCard);
        return updatedCard;
    }

    /**
     * Delete an existing card
     * @param id the id of the card to delete
     */
    @Override
    public void deleteCard(UUID id) {
        cardRepository.deleteById(id);
        streamBridge.send("cardDeleted-in-0", id);
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
}
