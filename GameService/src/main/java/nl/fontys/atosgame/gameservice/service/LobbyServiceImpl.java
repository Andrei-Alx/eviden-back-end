package nl.fontys.atosgame.gameservice.service;

import nl.fontys.atosgame.gameservice.model.Lobby;
import nl.fontys.atosgame.gameservice.repository.LobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LobbyServiceImpl implements LobbyService {
    private final LobbyRepository lobbyRepo;

    @Autowired
    public LobbyServiceImpl(LobbyRepository lobbyRepo)
    {
        this.lobbyRepo = lobbyRepo;
    }

    public void AddLobby(Lobby lobby)
    {
        this.lobbyRepo.save(lobby);
    }
}
