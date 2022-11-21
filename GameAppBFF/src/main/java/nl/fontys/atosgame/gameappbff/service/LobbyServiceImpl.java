package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.dto.LobbyResponseDto;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.repository.LobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for handling lobbies.
 * @author Eli
 */
@Service
public class LobbyServiceImpl implements LobbyService {

    private LobbyRepository lobbyRepository;

    public LobbyServiceImpl(@Autowired LobbyRepository lobbyRepository) {
        this.lobbyRepository = lobbyRepository;
    }
    /**
     * Create a new lobby in the database.
     *
     * @param lobby The lobby to create.
     * @return The created lobby.
     */
    @Override
    public Lobby createLobby(Lobby lobby) {
        return lobbyRepository.save(lobby);
    }
}
