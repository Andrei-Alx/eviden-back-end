package nl.fontys.atosgame.cardservice.service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import nl.fontys.atosgame.cardservice.dto.CreateCardSetDto;
import nl.fontys.atosgame.cardservice.event.produced.CardSetEvent;
import nl.fontys.atosgame.cardservice.model.Card;
import nl.fontys.atosgame.cardservice.model.CardSet;
import nl.fontys.atosgame.cardservice.repository.CardSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * Service that handles all card set requests
 * calls the repository to store the card set in the database
 * and sends a message to the event bus
 *
 * @author Eli
 */
@Service
public class CardSetServiceImpl implements CardSetService {

    private final CardSetRepository cardSetRepository;
    private final CardService cardService;
    private final StreamBridge streamBridge;

    public CardSetServiceImpl(
        @Autowired CardSetRepository cardSetRepository,
        @Autowired CardService cardService,
        @Autowired StreamBridge streamBridge
    ) {
        this.cardSetRepository = cardSetRepository;
        this.cardService = cardService;
        this.streamBridge = streamBridge;
    }

    /**
     * Creates a card set and sends a message to the event bus
     *
     * @param createCardSetDto the card set to create
     * @return the created card set
     */
    @Override
    public CardSet createCardSet(CreateCardSetDto createCardSetDto) {
        Collection<Card> cards = cardService.getCardsByIds(createCardSetDto.getCards());
        CardSet cardSet = new CardSet(
            null,
            createCardSetDto.getName(),
            cards,
            createCardSetDto.getTags(),
            true
        );

        cardSet = cardSetRepository.save(cardSet);
        return cardSet;
    }

    /**
     * Deletes a card set and sends a message to the event bus
     *
     * @param id the id of the card set to delete
     */
    @Override
    public void deleteCardSet(UUID id) {
        cardSetRepository.deleteById(id);
    }

    /**
     * Updates a card set and sends a message to the event bus
     *
     * @param cardSet the card set to update
     * @return the updated card set
     */
    @Override
    public CardSet updateCardSet(CardSet cardSet) {
        CardSet updatedCardSet = cardSetRepository.save(cardSet);
        return updatedCardSet;
    }

    @Override
    public List<CardSet> getAll() throws EntityNotFoundException {
        return cardSetRepository.findAll();
    }

    @Override
    public void produceCardSet() throws EntityNotFoundException {
        List<CardSet> currentCards = cardSetRepository.findAll();
        CardSetEvent event = new CardSetEvent();
        event.setCardSets(currentCards);
        streamBridge.send("produceCardSet-out-0", event);
    }


}
