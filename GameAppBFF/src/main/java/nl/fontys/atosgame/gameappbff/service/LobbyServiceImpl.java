package nl.fontys.atosgame.gameappbff.service;

import jakarta.persistence.EntityNotFoundException;
import nl.fontys.atosgame.gameappbff.controller.GameSocketController;
import nl.fontys.atosgame.gameappbff.controller.LobbySocketController;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.model.Player;
import nl.fontys.atosgame.gameappbff.repository.LobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Service for handling lobbies.
 * @author Eli, Aniek
 */
@Service
public class LobbyServiceImpl implements LobbyService {

    private final LobbyRepository lobbyRepository;
    private final GameSocketController gameSocketController;
    private final LobbySocketController lobbySocketController;

    public LobbyServiceImpl(
        @Autowired LobbyRepository lobbyRepository,
        @Autowired GameSocketController gameSocketController,
        @Autowired LobbySocketController lobbySocketController
    ) {
        this.lobbyRepository = lobbyRepository;
        this.gameSocketController = gameSocketController;
        this.lobbySocketController = lobbySocketController;
    }

    /**
     * Create a new lobby in the database.
     *
     * @param lobby The lobby to create.
     * @return The created lobby.
     */
    @Override
    public Lobby createLobby(Lobby lobby, UUID gameId) {
        Lobby lobby1 = lobbyRepository.save(lobby);
        return gameSocketController.lobbyCreated(gameId, lobby1);
    }

    /**
     * Delete a lobby in the database.
     *
     * @param lobbyId The lobby to delete.
     */
    @Override
    public void deleteLobby(UUID lobbyId) {
        lobbyRepository.deleteById(lobbyId);
    }

    /**
     * Add a player to a lobby.
     * @param lobbyId The lobby to add the player to.
     * @param player The player to add to the lobby.
     */
    @Override
    public Lobby addPlayer(UUID lobbyId, Player player) {
        Optional<Lobby> lobby = lobbyRepository.findById(lobbyId);
        if (lobby.isPresent()) {
            Lobby lobby1 = lobby.get();
            lobby1.addPlayer(player);
            lobbyRepository.save(lobby1);
            lobbySocketController.playerJoined(lobby1);
            return lobby1;
        } else {
            throw new EntityNotFoundException("Game not found");
        }
    }

    /**
     * Quit a player to a lobby.
     * @param lobbyId The lobby to quit the player to.
     * @param playerId The player to quit to the lobby.
     */
    @Override
    public void quitPlayer(UUID lobbyId, UUID playerId) {
        Optional<Lobby> lobby = lobbyRepository.findById(lobbyId);
        if (lobby.isPresent()) {
            Lobby lobby1 = lobby.get();
            lobby1.removePlayer(playerId);
            lobbyRepository.save(lobby1);
            lobbySocketController.playerQuit(lobbyId, playerId);
            // TODO: mark active playerrounds as 'quit' for that player
        } else {
            throw new EntityNotFoundException("Game not found");
        }
    }

    /**
     * Get a lobby by id.
     *
     * @param lobbyId The id of the lobby.
     * @return The lobby.
     */
    @Override
    public Optional<Lobby> getLobby(UUID lobbyId) {
        return lobbyRepository.findById(lobbyId);
    }
}
