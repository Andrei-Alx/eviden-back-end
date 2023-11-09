package nl.fontys.atosgame.lobbyservice.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import nl.fontys.atosgame.lobbyservice.dto.ExceptionDto;
import nl.fontys.atosgame.lobbyservice.dto.JoinRequestDto;
import nl.fontys.atosgame.lobbyservice.dto.JoinResponseDto;
import nl.fontys.atosgame.lobbyservice.exceptions.DuplicatePlayerException;
import nl.fontys.atosgame.lobbyservice.exceptions.EmptyPlayerNameException;
import nl.fontys.atosgame.lobbyservice.exceptions.LobbyFullException;
import nl.fontys.atosgame.lobbyservice.model.Lobby;
import nl.fontys.atosgame.lobbyservice.model.Player;
import nl.fontys.atosgame.lobbyservice.service.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for the lobby service
 * @author Eli
 */
@RestController
@RequestMapping("/api/lobby")
@CrossOrigin(origins = "*")
public class LobbyController {

    public LobbyController(@Autowired LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

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
                    schema = @Schema(implementation = JoinResponseDto.class)
                )
            ),
            @ApiResponse(
                responseCode = "406",
                description = "Lobby is full or player is already in lobby",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionDto.class)
                )
            ),
            @ApiResponse(responseCode = "404", description = "Lobby not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
        }
    )
    public ResponseEntity joinLobby(@RequestBody JoinRequestDto joinRequestDto) {
        Player player;
        Lobby lobby;
        try {
            player =
                lobbyService.joinLobby(
                    joinRequestDto.getLobbyCode(),
                    joinRequestDto.getPlayerName()
                );
            lobby = lobbyService.getByLobbyCode(joinRequestDto.getLobbyCode()).get();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (LobbyFullException | DuplicatePlayerException |EmptyPlayerNameException e) {
            return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ExceptionDto(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(new JoinResponseDto(lobby.getGameId(), player.getId()));
    }

    /**
     * Id: R-8
     * Quit a lobby, removes a player from the lobby
     */
    @PostMapping("/quit")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Quit the lobby"),
            @ApiResponse(responseCode = "404", description = "Lobby not found"),
        }
    )
    public ResponseEntity quitLobby(@RequestBody UUID lobbyId, UUID playerId) {
        try {
            lobbyService.quitLobby(lobbyId, playerId);
        } catch (EntityNotFoundException e) {
            ResponseEntity.notFound();
        }
        return ResponseEntity.ok().build();
    }
}
