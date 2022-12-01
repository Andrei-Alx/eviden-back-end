package nl.fontys.atosgame.gameservice.controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import nl.fontys.atosgame.gameservice.dto.CreateGameDto;
import nl.fontys.atosgame.gameservice.model.Game;
import nl.fontys.atosgame.gameservice.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

/**
 * Rest controller for the game service
 * @author Eli
 */
@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*")
public class GameController {

    private GameService gameService;

    public GameController(
            @Autowired GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Id: R-9
     * Create a game
     */
    @PostMapping("/create")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Created the game",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Game.class)
                )
            ),
            @ApiResponse(responseCode = "404", description = "Cardset not found"),
        }
    )
    public ResponseEntity<Game> createGame(@RequestBody CreateGameDto createGameDto) {
        try {
            Game game = gameService.createGame(createGameDto.getTitle(), createGameDto.getCompanyType(), createGameDto.getLobbySettings(), createGameDto.getRoundSettings());
            return ResponseEntity.ok(game);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Id: R-13
     * Start a game
     */
    @PutMapping("/start/{id}")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Started the game",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Game.class)
                )
            ),
            @ApiResponse(responseCode = "404", description = "Game not found"),
        }
    )
    public ResponseEntity<Game> startGame(@PathVariable UUID id) {
        try {
            Game game = gameService.startGame(id);
            return ResponseEntity.ok(game);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
