package nl.fontys.atosgame.gameappbff.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.*;

import nl.fontys.atosgame.gameappbff.enums.ShowResults;
import nl.fontys.atosgame.gameappbff.model.Game;
import nl.fontys.atosgame.gameappbff.model.Round;
import nl.fontys.atosgame.gameappbff.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/gameappbff")
public class GameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);
    private GameService gameService;

    private GameController(@Autowired GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Id: R-11
     *
     * Getting all games.
     *
     * @return all games.
     */
    @GetMapping("/games")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Getting all the games",
                content = @Content(
                    mediaType = "application/json"
                )
            ),
            @ApiResponse(responseCode = "404", description = "Games not found"),
        }
    )
    public ResponseEntity<List<Game>> getGames() {
        List<Game> games = gameService.getAllGames();
        return ResponseEntity.ok(games);
    }

    /**
     * Id: R-14
     * Get game by id
     */
    @GetMapping("/game/{id}")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Getting the game",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Game.class)
                )
            ),
            @ApiResponse(responseCode = "404", description = "Game not found"),
        }
    )
    public ResponseEntity<Game> getGameById(@PathVariable UUID id) {
        Optional<Game> game = gameService.getGame(id);

        for (Round round : game.get().getRounds()) {
            if (round.getRoundSettings().getShowPersonalOrGroupResults() == ShowResults.PERSONAL) {
                int indexOfFirstRound = 0;

                // Find the index of the first round with the same ShowResults
                for (int i = 0; i < game.get().getRounds().size(); i++) {
                    if (game.get().getRounds().get(i).getRoundSettings().getShowPersonalOrGroupResults() == ShowResults.PERSONAL) {
                        indexOfFirstRound = i;
                        break;
                    }
                }
                // Swap the current round with the first round
                Collections.swap(game.get().getRounds(), indexOfFirstRound, 0);
                break;
            }
        }


        LOGGER.info(String.format("game get event in game service => %s", game));
        if (game.isPresent()) {
            return ResponseEntity.ok(game.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
