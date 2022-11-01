package nl.fontys.atosgame.cardservice.service;

import nl.fontys.atosgame.cardservice.dto.CreateCardSetDto;
import nl.fontys.atosgame.cardservice.model.CardSet;

import java.util.UUID;

public interface CardSetService {
    CardSet createCardSet(CreateCardSetDto createCardSetDto);

    void deleteCardSet(UUID id);

    CardSet updateCardSet(CardSet cardSet);
}
