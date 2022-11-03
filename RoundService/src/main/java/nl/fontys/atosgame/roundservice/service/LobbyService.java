package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.model.Lobby;

import java.util.UUID;

/**
 * Service for managing lobbies.
 * @author Eli
 */
public interface LobbyService {
    /**
     * Create a new lobby
     * @return The created lobby
     */
    Lobby createLobby();

    /**
     * Get a lobby by id
     * @param id The id of the lobby
     * @return The lobby
     */
    Lobby getLobbyById(UUID id);

    /**
     * Add a player to a lobby
     * @param playerId The id of the player
     * @param lobbyId The id of the lobby
     * @return The updated lobby
     */
    Lobby addPlayerToLobby(UUID playerId, UUID lobbyId);

    /**
     * Remove a player from a lobby
     * @param playerId The id of the player
     * @param lobbyId The id of the lobby
     * @return The updated lobby
     */
    Lobby removePlayerFromLobby(UUID playerId, UUID lobbyId);
}
