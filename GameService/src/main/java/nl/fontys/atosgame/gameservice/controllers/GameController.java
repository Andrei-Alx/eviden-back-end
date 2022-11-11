package nl.fontys.atosgame.gameservice.controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import nl.fontys.atosgame.gameservice.model.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Rest controller for the game service
 * @author Eli
 */
@RestController
@RequestMapping("/api/game")
public class GameController {

    /**
     * Id: R-9
     * Create a game
     */
    @PostMapping("/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the game",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Game.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public ResponseEntity<Game> createGame() {
        // TODO: implement
        return ResponseEntity.ok(new Game(UUID.randomUUID(), null, new ArrayList<>(), "testCompany"));
    }
}
