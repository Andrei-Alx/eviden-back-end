package nl.fontys.atosgame.gameappbff.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Optional;
import nl.fontys.atosgame.gameappbff.dto.PlayerRoundDto;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.model.PlayerRoundResult;
import nl.fontys.atosgame.gameappbff.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for the results.
 *
 * @author Niek
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/results")
public class ResultController {

    private ResultService resultService;

    private ResultController(@Autowired ResultService resultService) {
        this.resultService = resultService;
    }

    /**
     * Id: R-10
     * Get player round results.
     */
    @GetMapping
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Getting a player round results ",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Lobby.class)
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Player round results not found"
            ),
        }
    )
    public ResponseEntity<PlayerRoundResult> getPlayerRoundResult(
        @RequestBody PlayerRoundDto playerRound
    ) {
        Optional<PlayerRoundResult> result = resultService.getPlayerRoundResult(
            playerRound.getRoundId(),
            playerRound.getPlayerId()
        );
        return result
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
