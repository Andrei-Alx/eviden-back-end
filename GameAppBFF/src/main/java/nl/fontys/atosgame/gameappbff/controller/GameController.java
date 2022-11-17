package nl.fontys.atosgame.gameappbff.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import nl.fontys.atosgame.gameappbff.dto.GameResponseDto;
import nl.fontys.atosgame.gameappbff.dto.LobbyResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/gameappbff")
public class GameController {

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
    public ResponseEntity<List<GameResponseDto>> getGames() {
        LobbyResponseDto lobby = new LobbyResponseDto(UUID.randomUUID(), "game1", "1234");
        GameResponseDto game = new GameResponseDto(UUID.randomUUID(), lobby, "Sports");

        LobbyResponseDto lobby1 = new LobbyResponseDto(UUID.randomUUID(), "game2", "5678");
        GameResponseDto game1 = new GameResponseDto(UUID.randomUUID(), lobby1, "Sports");

        List<GameResponseDto> games = new ArrayList<>();
        games.add(game);
        games.add(game1);

        return ResponseEntity.ok(games);
    }
}
