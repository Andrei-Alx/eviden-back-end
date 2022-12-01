package nl.fontys.atosgame.roundservice.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import nl.fontys.atosgame.roundservice.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("round")
public class CardController {
    @Autowired
    private RoundService roundService;


    @PostMapping("/likeCard")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Like A Card",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Round, Player, or PlayerRound not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error"),
            }
    )
     /**
     * R-17
     * This method likes the card (phase 1) for a given playerRound reference
     */
    public ResponseEntity likeCard(UUID playerid,UUID cardid,UUID gameid,UUID roundid){
        try {
            roundService.likeCard(playerid, cardid, gameid, roundid);
        }
        catch (EntityNotFoundException e){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cardDisliked")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Dislike A Card",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Round, Player, or PlayerRound not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error"),
            }
    )
    /**
     * R-18
     * This method dislikes the card (phase 1) for a given playerRound reference
     */
    public ResponseEntity dislikeCard(UUID playerid,UUID cardid,UUID gameid,UUID roundid){
        try {
            roundService.dislikeCard(playerid, cardid, gameid, roundid);
        }
        catch (EntityNotFoundException e){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cardsSelected")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "select cards",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Round, Player, Card(s) or PlayerRound not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error"),
            }
    )
    /**
     * R-19
     * This method selects the cards (phase 2) for a given playerRound reference
     */
    public ResponseEntity selectCards(UUID playerid, UUID[] cardids, UUID gameid, UUID roundid){
        try {
            roundService.selectCards(playerid, List.of(cardids), gameid, roundid);
        }
        catch (EntityNotFoundException e){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok().build();
    }
}
