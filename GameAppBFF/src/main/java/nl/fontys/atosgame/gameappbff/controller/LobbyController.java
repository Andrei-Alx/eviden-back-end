package nl.fontys.atosgame.gameappbff.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.service.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * Rest controller for the lobby.
 *
 * @author Eli
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/gameappbff")
public class LobbyController {

    private LobbyService lobbyService;

    private LobbyController(@Autowired LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    /**
     * Id: R-13
     * Get a lobby by id.
     */
    @GetMapping("/lobby/{id}")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Getting a lobby by id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Lobby.class))),
            @ApiResponse(responseCode = "404", description = "Lobby not found"),
        }
    )
    public ResponseEntity<Lobby> getLobby(@PathVariable UUID id) {
        Optional<Lobby> lobby = lobbyService.getLobby(id);
        return lobby.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
