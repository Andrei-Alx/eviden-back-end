package nl.fontys.atosgame.gameappbff.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import nl.fontys.atosgame.gameappbff.model.Game;
import nl.fontys.atosgame.gameappbff.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/gameappbff")
public class GameController {

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
                    //                                    ,
                    //                                    schema = @Schema(implementation = GameResponseDto.class)
                )
            ),
            @ApiResponse(responseCode = "404", description = "Games not found"),
        }
    )
    public ResponseEntity<List<Game>> getGames() {

        List<Game> games = gameService.getAllGames();

//        LobbyResponseDto lobby = new LobbyResponseDto(UUID.randomUUID(), "1234");
//        GameResponseDto game = new GameResponseDto(
//            UUID.randomUUID(),
//            lobby,
//            "game1",
//            "Sports"
//        );
//
//        LobbyResponseDto lobby1 = new LobbyResponseDto(UUID.randomUUID(), "5678");
//        GameResponseDto game1 = new GameResponseDto(
//            UUID.randomUUID(),
//            lobby1,
//            "game2",
//            "Sports"
//        );
//
//        List<GameResponseDto> games = new ArrayList<>();
//        games.add(game);
//        games.add(game1);

        return ResponseEntity.ok(games);
    }
}
