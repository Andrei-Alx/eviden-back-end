package nl.fontys.atosgame.roundservice.service;

import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.EntityNotFoundException;
import nl.fontys.atosgame.roundservice.model.Lobby;

/**
 * Service for managing lobbies.
 * @author Eli
 */
public interface LobbyService {
    /**
     * Create a new lobby
     * @param lobby The lobby to create
     * @return The created lobby
     */
    Lobby createLobby(Lobby lobby);

    /**
     * Get a lobby by id
     * @param id The id of the lobby
     * @return The lobby
     */
    Optional<Lobby> getLobbyById(UUID id);

    /**
     * Add a player to a lobby
     * @param playerId The id of the player
     * @param lobbyId The id of the lobby
     * @return The updated lobby
     */
    Lobby addPlayerToLobby(UUID playerId, UUID lobbyId) throws EntityNotFoundException;

    /**
     * Remove a player from a lobby
     * @param playerId The id of the player
     * @param lobbyId The id of the lobby
     * @return The updated lobby
     */
    Lobby removePlayerFromLobby(UUID playerId, UUID lobbyId)
        throws EntityNotFoundException;

    /**
     * Get the lobby of a game
     * @param gameId The id of the game
     * @return An optional containing the lobby if found
     */
    Optional<Lobby> getLobbyByGameId(UUID gameId);
}
