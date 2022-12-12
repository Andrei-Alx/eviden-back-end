package nl.fontys.atosgame.roundservice.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import nl.fontys.atosgame.roundservice.dto.CardLikeRequestDto;
import nl.fontys.atosgame.roundservice.dto.CardSubmitRequestDto;
import nl.fontys.atosgame.roundservice.dto.StartNextRoundDto;
import nl.fontys.atosgame.roundservice.service.GameService;
import nl.fontys.atosgame.roundservice.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/round")
@CrossOrigin(origins = "*")
public class CardController {

    @Autowired
    private RoundService roundService;

    @Autowired
    private GameService gameService;

    @PostMapping("/likeCard")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Like A Card",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CardLikeRequestDto.class)
                )
            ),
            @ApiResponse(responseCode = "404", description = "Round not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
        }
    )
    /**
     * R-17
     * This method likes the card (phase 1) for a given playerRound reference
     */
    public ResponseEntity likeCard(@RequestBody CardLikeRequestDto cardLikeRequestDto) {
        try {
            roundService.likeCard(
                cardLikeRequestDto.getPlayerId(),
                cardLikeRequestDto.getCardId(),
                cardLikeRequestDto.getGameId(),
                cardLikeRequestDto.getRoundId()
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/dislikeCard")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Dislike A Card",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CardLikeRequestDto.class)
                )
            ),
            @ApiResponse(responseCode = "404", description = "Round not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
        }
    )
    /**
     * R-18
     * This method dislikes the card (phase 1) for a given playerRound reference
     */
    public ResponseEntity dislikeCard(
        @RequestBody CardLikeRequestDto cardLikeRequestDto
    ) {
        try {
            roundService.dislikeCard(
                cardLikeRequestDto.getPlayerId(),
                cardLikeRequestDto.getCardId(),
                cardLikeRequestDto.getGameId(),
                cardLikeRequestDto.getRoundId()
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/selectCards")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "selected cards",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CardSubmitRequestDto.class)
                )
            ),
            @ApiResponse(responseCode = "404", description = "Round not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
        }
    )
    /**
     * R-19
     * This method selects the cards (phase 2) for a given playerRound reference
     */
    public ResponseEntity selectedCards(
        @RequestBody CardSubmitRequestDto cardSubmitRequestDto
    ) {
        try {
            roundService.selectCards(
                cardSubmitRequestDto.getPlayerId(),
                cardSubmitRequestDto.getCardIds(),
                cardSubmitRequestDto.getGameId(),
                cardSubmitRequestDto.getRoundId()
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/startnextround")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "next round",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CardSubmitRequestDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Game not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error"),
            }
    )
    /**
     * R-22
     * This method starts the next round manually
     */
    public ResponseEntity startNextRound(
            @RequestBody StartNextRoundDto startNextRoundDto
    ) {
        try {
            roundService.startNextRound(startNextRoundDto.getGameId());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
