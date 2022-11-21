package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.controller.GameSocketController;
import nl.fontys.atosgame.gameappbff.controller.LobbySocketController;
import nl.fontys.atosgame.gameappbff.dto.PlayerJoinedDto;
import nl.fontys.atosgame.gameappbff.dto.PlayerQuitDto;
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
    private LobbySocketController lobbySocketController;

    public LobbyServiceImpl(@Autowired LobbyRepository lobbyRepository, @Autowired GameSocketController gameSocketController, @Autowired LobbySocketController lobbySocketController) {
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

    /**
     * Add a player to a lobby.
     * @param lobbyId The lobby to add the player to.
     * @param playerId The player to add to the lobby.
     */
    @Override
    public Lobby addPlayer(UUID lobbyId, UUID playerId){
        Lobby lobby = null;
        if(lobbyRepository.findById(lobbyId).isPresent()){
            lobby = lobbyRepository.findById(lobbyId).get();
            lobby.addPlayer(playerId);
            lobbyRepository.save(lobby);
            lobbySocketController.playerJoined(lobbyId, playerId);
        }

        return lobby;
    }

    /**
     * Quit a player to a lobby.
     * @param lobbyId The lobby to quit the player to.
     * @param playerId The player to quit to the lobby.
     */
    @Override
    public void quitPlayer(UUID lobbyId, UUID playerId){
        if(lobbyRepository.findById(lobbyId).isPresent()){
            Lobby lobby = lobbyRepository.findById(lobbyId).get();
            lobby.removePlayer(playerId);
            lobbyRepository.save(lobby);
            lobbySocketController.playerQuit(lobbyId, playerId);
        }
    }
}
