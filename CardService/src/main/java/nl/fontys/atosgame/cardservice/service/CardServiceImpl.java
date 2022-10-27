package nl.fontys.atosgame.cardservice.service;

import nl.fontys.atosgame.cardservice.dto.CreateCardDto;
import nl.fontys.atosgame.cardservice.model.Card;
import nl.fontys.atosgame.cardservice.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card createCard(CreateCardDto createCardDto) {
        return cardRepository.save(new Card(null, createCardDto.getTags(), createCardDto.getTranslations()));
    }

    @Override
    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public void deleteCard(UUID id) {
        cardRepository.deleteById(id);
    }
}
