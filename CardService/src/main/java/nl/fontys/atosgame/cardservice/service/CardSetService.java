package nl.fontys.atosgame.cardservice.service;

import nl.fontys.atosgame.cardservice.dto.CreateCardSetDto;
import nl.fontys.atosgame.cardservice.model.CardSet;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public interface CardSetService {
    CardSet createCardSet(CreateCardSetDto createCardSetDto);

    void deleteCardSet(UUID id) throws EntityNotFoundException;

    CardSet updateCardSet(CardSet cardSet) throws EntityNotFoundException;
}
