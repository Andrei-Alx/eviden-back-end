package nl.fontys.atosgame.cardservice.controller;

import nl.fontys.atosgame.cardservice.dto.CreateCardSetDto;
import nl.fontys.atosgame.cardservice.model.CardSet;
import nl.fontys.atosgame.cardservice.service.CardService;
import nl.fontys.atosgame.cardservice.service.CardSetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardSetControllerTest {

    private CardSetService cardSetService;
    private CardSetController cardSetController;

    @BeforeEach
    void setUp() {
        cardSetService = mock(CardSetService.class);
        cardSetController = new CardSetController(cardSetService);
    }

    @Test
    void createCard200() {
        CreateCardSetDto createCardSetDto = new CreateCardSetDto();
        CardSet cardSet = new CardSet();
        when(cardSetService.createCardSet(createCardSetDto)).thenReturn(cardSet);

        var response = cardSetController.createCardSet(createCardSetDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cardSet, response.getBody());
    }

    @Test
    void createCard400() {
        CreateCardSetDto createCardSetDto = new CreateCardSetDto();
        when(cardSetService.createCardSet(createCardSetDto)).thenThrow(new RuntimeException());

        var response = cardSetController.createCardSet(createCardSetDto);

        assertEquals(400, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void updateCard200() {
        CardSet cardSet = new CardSet();
        when(cardSetService.updateCardSet(cardSet)).thenReturn(cardSet);

        var response = cardSetController.updateCardSet(cardSet);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cardSet, response.getBody());
    }

    @Test
    void updateCard404() {
        CardSet cardSet = new CardSet();
        when(cardSetService.updateCardSet(cardSet)).thenThrow(new EntityNotFoundException());

        var response = cardSetController.updateCardSet(cardSet);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void deleteCard200() {
        UUID id = UUID.randomUUID();

        var response = cardSetController.deleteCardSet(id);

        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void deleteCard404() {
        UUID id = UUID.randomUUID();
        doThrow(new EntityNotFoundException()).when(cardSetService).deleteCardSet(id);

        var response = cardSetController.deleteCardSet(id);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}