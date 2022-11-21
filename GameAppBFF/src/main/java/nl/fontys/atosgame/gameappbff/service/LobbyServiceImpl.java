package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.controller.GameSocketController;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.repository.LobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service for handling lobbies.
 * @author Eli, Aniek
 */
@Service
public class LobbyServiceImpl implements LobbyService {

    private LobbyRepository lobbyRepository;
    private GameSocketController gameSocketController;

    public LobbyServiceImpl(@Autowired LobbyRepository lobbyRepository, @Autowired GameSocketController gameSocketController) {
        this.lobbyRepository = lobbyRepository;
        this.gameSocketController = gameSocketController;
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
        return gameSocketController.lobby(gameId, lobby1);
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
}
