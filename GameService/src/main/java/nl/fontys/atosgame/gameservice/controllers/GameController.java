package nl.fontys.atosgame.gameservice.controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.Collections;
import java.util.UUID;

import jakarta.persistence.EntityNotFoundException;
import nl.fontys.atosgame.gameservice.dto.CreateGameDto;
import nl.fontys.atosgame.gameservice.enums.ShowResults;
import nl.fontys.atosgame.gameservice.exceptions.EmptyStringException;
import nl.fontys.atosgame.gameservice.model.Game;
import nl.fontys.atosgame.gameservice.model.Round;
import nl.fontys.atosgame.gameservice.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for the game service
 * @author Eli
 */
@RestController
@RequestMapping("/api/game")
public class GameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);
    private GameService gameService;

    public GameController(@Autowired GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Id: R-9
     * Create a game
     */
    @PostMapping("/create")
    public ResponseEntity<Game> createGame(@RequestBody CreateGameDto createGameDto) {

        LOGGER.info(String.format("given DTO when creating a game => %s", createGameDto));
        try {
            Game game = gameService.createGame(
                    createGameDto.getTitle(),
                    createGameDto.getCompanyType(),
                    createGameDto.getLobbySettings(),
                    createGameDto.getRoundSettings()
            );
            return ResponseEntity.ok(game);
        } catch (EntityNotFoundException | EmptyStringException e) {
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
            for (Round round : game.getRounds()) {
                if (round.getRoundSettings().getShowPersonalOrGroupResults() == ShowResults.PERSONAL) {
                    int indexOfFirstRound = 0;

                    // Find the index of the first round with the same ShowResults
                    for (int i = 0; i < game.getRounds().size(); i++) {
                        if (game.getRounds().get(i).getRoundSettings().getShowPersonalOrGroupResults() == ShowResults.PERSONAL) {
                            indexOfFirstRound = i;
                            break;
                        }
                    }
                    // Swap the current round with the first round
                    Collections.swap(game.getRounds(), indexOfFirstRound, 0);
                    break;
                }
            }
            LOGGER.info(String.format("starting game event gameservice => %s", game));
            return ResponseEntity.ok(game);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Id: R-?
     * End a game
     */
    @PutMapping("/end/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ended the game",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Game.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Game not found"),
            }
    )
    public ResponseEntity<Game> endGame(@PathVariable UUID id) {
        try {
            Game game = gameService.endGame(id);
            return ResponseEntity.ok(game);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/abort/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Aborted the game",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Game.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Game not found"),
            }
    )
    public ResponseEntity<Game> abortGame(@PathVariable UUID id) {
        try {
            Game game = gameService.endGame(id);
            return ResponseEntity.ok(game);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
