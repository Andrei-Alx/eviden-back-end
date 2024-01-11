package nl.fontys.atosgame.cardservice.service;

import java.util.Collection;
import java.util.UUID;

import jakarta.persistence.EntityNotFoundException;
import nl.fontys.atosgame.cardservice.model.Card;

public interface CardService {
    Card createCard(Card card);

    Card updateCard(Card card) throws EntityNotFoundException;

    void deleteCard(UUID id) throws EntityNotFoundException;

    Collection<Card> getCardsByIds(Collection<UUID> ids);

    void deleteCards(Collection<UUID> ids) throws EntityNotFoundException;
}
