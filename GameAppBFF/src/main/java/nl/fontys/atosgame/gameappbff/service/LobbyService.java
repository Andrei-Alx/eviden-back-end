package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.model.Player;
import nl.fontys.atosgame.gameappbff.model.Lobby;

import java.util.Optional;
import java.util.UUID;

/**
 * Service for handling lobbies.
 */
public interface LobbyService {
    /**
     * Create a new lobby in the database.
     * @param lobby The lobby to create.
     * @return The created lobby.
     */
    Lobby createLobby(Lobby lobby, UUID gameId);

    /**
     * Delete a lobby in the database.
     * @param lobbyId The lobby to delete.
     */
    void deleteLobby(UUID lobbyId);

    /**
     * Add a player to a lobby.
     * @param lobbyId The lobby to add the player to.
     * @param player The player to add to the lobby.
     */
    Lobby addPlayer(UUID lobbyId, Player player);

    /**
     * Quit a player to a lobby.
     * @param lobbyId The lobby to quit the player to.
     * @param playerId The player to quit to the lobby.
     */
    void quitPlayer(UUID lobbyId, UUID playerId);

    /**
     * Get a lobby by id.
     * @param lobbyId The id of the lobby.
     * @return The lobby.
     */
    Optional<Lobby> getLobby(UUID lobbyId);
}
