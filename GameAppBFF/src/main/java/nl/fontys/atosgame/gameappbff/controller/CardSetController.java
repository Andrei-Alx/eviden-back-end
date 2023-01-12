package nl.fontys.atosgame.gameappbff.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import nl.fontys.atosgame.gameappbff.enums.CardSetType;
import nl.fontys.atosgame.gameappbff.model.CardSet;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.service.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/gameappbff")
public class CardSetController {

    private CardSetService cardSetService;

    private CardSetController(@Autowired CardSetService cardSetService) {
        this.cardSetService = cardSetService;
    }
    /**
     * Id: R-20
     * Get cardsets by type.
     * Author: Niek
     */
    @GetMapping("/cardSets/{type}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Getting a cardset by type",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CardSet.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Cardsets not found"),
            }
    )
    public ResponseEntity<ArrayList<CardSet>> getCardSetsByType(@PathVariable CardSetType type) {
        Optional<ArrayList<CardSet>> cardsets = cardSetService.getCardSetsByType(type);
        return cardsets
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
