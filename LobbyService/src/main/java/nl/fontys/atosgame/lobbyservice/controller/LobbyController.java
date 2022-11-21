package nl.fontys.atosgame.lobbyservice.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.ArrayList;
import java.util.UUID;
import nl.fontys.atosgame.lobbyservice.dto.JoinRequestDto;
import nl.fontys.atosgame.lobbyservice.model.Lobby;
import nl.fontys.atosgame.lobbyservice.model.LobbySettings;
import nl.fontys.atosgame.lobbyservice.model.Player;
import nl.fontys.atosgame.lobbyservice.service.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for the lobby service
 * @author Eli
 */
@RestController
@RequestMapping("/api/lobby")
public class LobbyController {

    public LobbyController(@Autowired LobbyService lobbyService){ this.lobbyService = lobbyService;}

    private LobbyService lobbyService;
    /**
     * Id: R-7
     * Join a lobby
     */
    @PostMapping("/join")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Joined the lobby",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Lobby.class)
                )
            ),
            @ApiResponse(responseCode = "406", description = "Lobby is full"),
            @ApiResponse(responseCode = "404", description = "Lobby not found"),
        }
    )
    /**
     * R-7
     * this method adds a player to the lobby
     */
    public ResponseEntity<Lobby> joinLobby(@RequestBody JoinRequestDto joinRequestDto) throws Exception {
        Lobby lobby = lobbyService.joinLobby(joinRequestDto.getLobbyCode(), joinRequestDto.getPlayerName());
        return ResponseEntity.ok(
            new Lobby(
                lobby.getId(),
                lobby.getPlayers(),
                    lobby.getLobbyCode(),
                lobby.getLobbySettings(),
                lobby.getGameId()
            )
        );
    }

    /**
     * Id: R-8
     * Quit a lobby
     */
    @PostMapping("/quit")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Quit the lobby"),
            @ApiResponse(responseCode = "404", description = "Lobby not found"),
        }
    )

    /**
     * R-9
     * This method removes a player from the lobby
     */
    public ResponseEntity quitLobby(@RequestBody UUID lobbyId, UUID playerId) {
        lobbyService.quitLobby(lobbyId, playerId);
        return ResponseEntity.ok().build();
    }
}
