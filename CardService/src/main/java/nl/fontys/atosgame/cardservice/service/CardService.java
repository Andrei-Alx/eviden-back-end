package nl.fontys.atosgame.cardservice.service;

import java.util.List;
import java.util.UUID;
import nl.fontys.atosgame.cardservice.model.Card;
import jakarta.persistence.EntityNotFoundException;

public interface CardService {
    Card createCard(Card card);

    List<Card> createCards(List<Card> cards);

    Card updateCard(Card card) throws EntityNotFoundException;

    void deleteCard(UUID id) throws EntityNotFoundException;

    List<Card> getCardsByIds(List<UUID> ids);

    void deleteCards(List<UUID> ids) throws EntityNotFoundException;
}
