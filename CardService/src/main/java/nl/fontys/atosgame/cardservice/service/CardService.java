package nl.fontys.atosgame.cardservice.service;

import nl.fontys.atosgame.cardservice.dto.CreateCardDto;
import nl.fontys.atosgame.cardservice.model.Card;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public interface CardService {
    Card createCard(CreateCardDto createCardDto);

    Card updateCard(Card card) throws EntityNotFoundException;

    void deleteCard(UUID id) throws EntityNotFoundException;
}
