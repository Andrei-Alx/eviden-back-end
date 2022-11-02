package nl.fontys.atosgame.cardservice.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import nl.fontys.atosgame.cardservice.dto.CreateCardDto;
import nl.fontys.atosgame.cardservice.model.Card;
import nl.fontys.atosgame.cardservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;


/**
 * Controller for the CardService
 * Performs CRUD operations on cards
 * @author Eli
 */
@RestController
@RequestMapping("/api/cards")
public class CardController {


    private CardService cardService;

    public CardController(@Autowired CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Card created", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = Card.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @io.swagger.v3.oas.annotations.media.Content),
    })
    public ResponseEntity<Card> createCard(@RequestBody CreateCardDto createCardDto) {
        try {
            return ResponseEntity.ok(cardService.createCard(createCardDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Card updated", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = Card.class))),
            @ApiResponse(responseCode = "404", description = "Card not found", content = @io.swagger.v3.oas.annotations.media.Content),
    })
    public ResponseEntity<Card> updateCard(@RequestBody Card card) {
        try {
            cardService.updateCard(card);
            return ResponseEntity.ok(card);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Card deleted", content = @io.swagger.v3.oas.annotations.media.Content),
            @ApiResponse(responseCode = "404", description = "Card not found", content = @io.swagger.v3.oas.annotations.media.Content),
    })
    public ResponseEntity<String> deleteCard(@PathVariable UUID id) {
        try {
            cardService.deleteCard(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
