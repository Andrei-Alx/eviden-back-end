package nl.fontys.atosgame.lobbyservice.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import nl.fontys.atosgame.lobbyservice.dto.JoinRequestDto;
import nl.fontys.atosgame.lobbyservice.model.Lobby;
import nl.fontys.atosgame.lobbyservice.model.LobbySettings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Rest controller for the lobby service
 * @author Eli
 */
@RestController
@RequestMapping("/api/lobby")
public class LobbyController {

    /**
     * Id: R-7
     * Creates a new lobby
     * @param joinRequestDto
     * @return
     */
    @PostMapping("/join")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Joined the lobby"
                    , content = @Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Lobby.class))),
            @ApiResponse(responseCode = "406", description = "Lobby is full"),
            @ApiResponse(responseCode = "404", description = "Lobby not found")
    })
    public ResponseEntity<Lobby> joinLobby(@RequestBody JoinRequestDto joinRequestDto) {
        // TODO: implement
        return ResponseEntity.ok(new Lobby(UUID.randomUUID(), new ArrayList<>(), "testCode", new LobbySettings()));
    }
}
