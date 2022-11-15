package nl.fontys.atosgame.lobbyservice.service;

import java.util.UUID;
import nl.fontys.atosgame.lobbyservice.model.Lobby;
import nl.fontys.atosgame.lobbyservice.model.LobbySettings;
import nl.fontys.atosgame.lobbyservice.repository.LobbyRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    public LobbyServiceImpl(
        @Autowired LobbyRepository lobbyRepository,
        @Autowired LobbyCodeGenerator lobbyCodeGenerator
    ) {
        this.lobbyRepository = lobbyRepository;
        this.lobbyCodeGenerator = lobbyCodeGenerator;
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
        return lobbyRepository.save(lobby);
    }
}
