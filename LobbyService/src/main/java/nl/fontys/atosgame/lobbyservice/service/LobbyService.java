package nl.fontys.atosgame.lobbyservice.service;

import java.util.UUID;
import nl.fontys.atosgame.lobbyservice.model.Lobby;
import nl.fontys.atosgame.lobbyservice.model.LobbySettings;

/**
 * Service for handling lobbies.
 */
public interface LobbyService {
    /**
     * Creates a new lobby.
     * @param settings The settings for the lobby.
     * @param gameId The id of the game.
     * @return The created lobby.
     */
    Lobby createLobby(LobbySettings settings, UUID gameId);
}
