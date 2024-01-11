package nl.fontys.atosgame.cardservice.controller;

import jakarta.persistence.EntityNotFoundException;
import nl.fontys.atosgame.cardservice.dto.CreateCardDto;
import nl.fontys.atosgame.cardservice.model.Card;
import nl.fontys.atosgame.cardservice.model.Tag;
import nl.fontys.atosgame.cardservice.model.Translation;
import nl.fontys.atosgame.cardservice.service.CardService;
import nl.fontys.atosgame.cardservice.service.CardSetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CardControllerTest {

    CardController cardController;
    CardService cardService;
    CardSetService cardSetService;


    @BeforeEach
    void setUp() {
        cardService = mock(CardService.class);
        cardController = new CardController(cardService, cardSetService);
    }

    @Test
    void createCard200() {
        CreateCardDto createCardDto = new CreateCardDto(null, null);
        Card card = new Card();
        when(cardService.createCard(new Card(null, createCardDto.getTags(),createCardDto.getTranslations(), true))).thenReturn(card);

        var response = cardController.createCard(createCardDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(card, response.getBody());
    }

    @Test
    void createCard400() {
        List<Tag> tags = List.of(new Tag(), new Tag());
        List<Translation> translations = List.of(new Translation(), new Translation());
        CreateCardDto createCardDto = new CreateCardDto(tags, translations);
        when(cardService.createCard(new Card(null, createCardDto.getTags(),createCardDto.getTranslations(), true))).thenThrow(new RuntimeException());

        var response = cardController.createCard(createCardDto);

        assertEquals(400, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void updateCard200() {
        Card card = new Card();
        when(cardService.updateCard(card)).thenReturn(card);

        var response = cardController.updateCard(card);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(card, response.getBody());
    }

    @Test
    void updateCard404() {
        Card card = new Card();
        when(cardService.updateCard(card)).thenThrow(new EntityNotFoundException());

        var response = cardController.updateCard(card);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void deleteCard200() {
        UUID id = UUID.randomUUID();

        var response = cardController.deleteCard(id);

        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void deleteCard404() {
        UUID id = UUID.randomUUID();
        Mockito
            .doThrow(new EmptyResultDataAccessException(1))
            .when(cardService)
            .deleteCard(id);

        var response = cardController.deleteCard(id);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
