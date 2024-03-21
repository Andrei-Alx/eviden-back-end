package nl.fontys.atosgame.gameservice.service;

import nl.fontys.atosgame.gameservice.model.Lobby;
import nl.fontys.atosgame.gameservice.repository.LobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LobbyServiceImpl implements LobbyService {
    private final LobbyRepository lobbyRepository;

    @Autowired
    public LobbyServiceImpl(LobbyRepository lobbyRepository)
    {
        this.lobbyRepository = lobbyRepository;
    }

    public void addLobby(Lobby lobby)
    {
        this.lobbyRepository.save(lobby);
    }
}
