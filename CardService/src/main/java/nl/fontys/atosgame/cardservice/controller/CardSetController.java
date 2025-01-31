package nl.fontys.atosgame.cardservice.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import nl.fontys.atosgame.cardservice.dto.CreateCardSetDto;
import nl.fontys.atosgame.cardservice.model.CardSet;
import nl.fontys.atosgame.cardservice.service.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller that handles all card set requests
 * Performs CRUD operations on card sets
 * @author Eli
 */
@RestController
@RequestMapping("/api/cardsets")
public class CardSetController {

    private CardSetService cardSetService;

    public CardSetController(@Autowired CardSetService cardSetService) {
        this.cardSetService = cardSetService;
    }

    /**
     * Id: R-4
     * create a new card set
     * @param createCardSetDto
     * @return
     */
    @PostMapping("/create")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Cardset created",
                content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CardSet.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid input",
                content = @io.swagger.v3.oas.annotations.media.Content
            ),
        }
    )
    public ResponseEntity<CardSet> createCardSet(
        @RequestBody CreateCardSetDto createCardSetDto
    ) {
        // TODO: Cards should be validated, give response when card ids do not exist
        try {
            return ResponseEntity.ok(cardSetService.createCardSet(createCardSetDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Id: R-5
     * Update a card set
     * @return
     */
    @PutMapping("/update")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Cardset updated",
                content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CardSet.class)
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Cardset not found",
                content = @io.swagger.v3.oas.annotations.media.Content
            ),
        }
    )
    public ResponseEntity<CardSet> updateCardSet(@RequestBody CardSet cardSet) {
        try {
            cardSetService.updateCardSet(cardSet);
            return ResponseEntity.ok(cardSet);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Id: R-6
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Cardset deleted",
                content = @io.swagger.v3.oas.annotations.media.Content
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Cardset not found",
                content = @io.swagger.v3.oas.annotations.media.Content
            ),
        }
    )
    public ResponseEntity deleteCardSet(@PathVariable UUID id) {
        try {
            cardSetService.deleteCardSet(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
