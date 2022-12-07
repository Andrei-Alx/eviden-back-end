package nl.fontys.atosgame.lobbyservice.service;

import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.lobbyservice.model.Lobby;
import nl.fontys.atosgame.lobbyservice.model.LobbySettings;
import nl.fontys.atosgame.lobbyservice.model.Player;

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

    /**
     * Deletes a lobby by gameId.
     * @param gameId The gameid of the lobby.
     */
    void deleteLobbyByGameId(UUID gameId);

    /**
     * This method adds the player to the lobby
     * @param lobbyCode
     * @param playerName
     * @return the joined player
     * @throws Exception
     */
    Player joinLobby(String lobbyCode, String playerName) throws Exception;

    /**
     * This method removes a player from the lobby
     * @param lobbyId
     * @param playerId
     */
    void quitLobby(UUID lobbyId, UUID playerId);

    /**
     * This method returns the lobby by lobbyCode
     * @param lobbyCode The lobbyCode of the lobby
     * @return the lobby
     */
    Optional<Lobby> getByLobbyCode(String lobbyCode);
}
