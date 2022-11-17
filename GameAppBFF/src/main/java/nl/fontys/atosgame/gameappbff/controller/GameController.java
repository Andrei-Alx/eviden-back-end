package nl.fontys.atosgame.gameappbff.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import nl.fontys.atosgame.gameappbff.dto.GameResponseDto;
import nl.fontys.atosgame.gameappbff.dto.LobbyResponseDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/gameappbff")
public class GameController {

    /**
     * Getting all games.
     *
     * @return all games.
     */
    @GetMapping("/games")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Getting all the games"),
                    @ApiResponse(responseCode = "404", description = "No games found"),
            }
    )
    public GameResponseDto getGames() {
        LobbyResponseDto lobby = new LobbyResponseDto();
        lobby.setId("1");
        lobby.setTitle("TestLobby");
        lobby.setCode("1234");

        GameResponseDto game = new GameResponseDto();
        game.setLobby(lobby);
        game.setId("1");
        game.setCompanyType("banking");
        return game;
    }
}
