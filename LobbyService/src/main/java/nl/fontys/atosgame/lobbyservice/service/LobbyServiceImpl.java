package nl.fontys.atosgame.lobbyservice.service;

import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.lobbyservice.dto.LobbyDeletedDto;
import nl.fontys.atosgame.lobbyservice.model.Lobby;
import nl.fontys.atosgame.lobbyservice.model.LobbySettings;
import nl.fontys.atosgame.lobbyservice.repository.LobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

/**
 * Service for handling lobbies.
 *
 * @author Eli
 */
@Service
public class LobbyServiceImpl implements LobbyService {

    private final LobbyRepository lobbyRepository;

    private final LobbyCodeGenerator lobbyCodeGenerator;

    private final StreamBridge streamBridge;

    public LobbyServiceImpl(
        @Autowired LobbyRepository lobbyRepository,
        @Autowired LobbyCodeGenerator lobbyCodeGenerator,
        @Autowired StreamBridge streamBridge
    ) {
        this.lobbyRepository = lobbyRepository;
        this.lobbyCodeGenerator = lobbyCodeGenerator;
        this.streamBridge = streamBridge;
    }

    /**
     * Creates a new lobby.
     *
     * @param settings The settings for the lobby.
     * @param gameId   The id of the game.
     * @return The created lobby.
     */
    @Override
    public Lobby createLobby(LobbySettings settings, UUID gameId) {
        Lobby lobby = new Lobby();
        lobby.setLobbySettings(settings);
        lobby.setGameId(gameId);
        lobby.setLobbyCode(lobbyCodeGenerator.generateLobbyCode());
        Lobby createdLobby = lobbyRepository.save(lobby);
        streamBridge.send("produceLobbyCreated-in-0", createdLobby);
        return lobbyRepository.save(lobby);
    }

    /**
     * Deletes a lobby by gameId.
     *
     * @param gameId The gameid of the lobby.
     */
    @Override
    public void deleteLobbyByGameId(UUID gameId) {
        UUID lobbyId = lobbyRepository.getByGameId(gameId).getId();
        lobbyRepository.deleteByGameId(gameId);
        streamBridge.send(
            "produceLobbyDeleted-in-0",
            new LobbyDeletedDto(lobbyId, gameId)
        );
    }
}
