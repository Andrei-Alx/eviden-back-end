package nl.fontys.atosgame.cardservice.service.interfaces;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.EntityNotFoundException;
import nl.fontys.atosgame.cardservice.dto.CreateCardSetDto;
import nl.fontys.atosgame.cardservice.model.CardSet;

public interface CardSetService {
    CardSet createCardSet(CreateCardSetDto createCardSetDto);

    void deleteCardSet(UUID id) throws EntityNotFoundException;

    CardSet updateCardSet(CardSet cardSet) throws EntityNotFoundException;

    List<CardSet> getAll() throws EntityNotFoundException;

    void produceCardSet() throws EntityNotFoundException;
}
