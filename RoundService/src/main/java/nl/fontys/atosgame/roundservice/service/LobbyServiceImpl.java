package nl.fontys.atosgame.roundservice.service;

import java.util.Optional;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Lob;
import nl.fontys.atosgame.roundservice.model.Lobby;
import nl.fontys.atosgame.roundservice.repository.LobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LobbyServiceImpl implements LobbyService {

    private final LobbyRepository lobbyRepository;

    public LobbyServiceImpl(@Autowired LobbyRepository lobbyRepository) {
        this.lobbyRepository = lobbyRepository;
    }

    /**
     * Create a new lobby
     *
     * @param lobby The lobby to create
     * @return The created lobby
     */
    @Override
    public Lobby createLobby(Lobby lobby) {
        return lobbyRepository.save(lobby);
    }

    /**
     * Get a lobby by id
     *
     * @param id The id of the lobby
     * @return The lobby
     */
    @Override
    public Optional<Lobby> getLobbyById(UUID id) {
        return lobbyRepository.findById(id);
    }

    /**
     * Add a player to a lobby
     *
     * @param playerId The id of the player
     * @param lobbyId  The id of the lobby
     * @return The updated lobby
     */
    @Override
    public Lobby addPlayerToLobby(UUID playerId, UUID lobbyId)
        throws EntityNotFoundException {
        Optional<Lobby> lobbyOptional = this.getLobbyById(lobbyId);
        if (lobbyOptional.isPresent()) {
            Lobby lobby = lobbyOptional.get();
            lobby.addPlayer(playerId);
            return lobbyRepository.save(lobby);
        } else {
            throw new EntityNotFoundException("Lobby not found");
        }
    }

    /**
     * Remove a player from a lobby
     *
     * @param playerId The id of the player
     * @param lobbyId  The id of the lobby
     * @return The updated lobby
     */
    @Override
    public Lobby removePlayerFromLobby(UUID playerId, UUID lobbyId)
        throws EntityNotFoundException {
        Optional<Lobby> lobbyOptional = this.getLobbyById(lobbyId);
        if (lobbyOptional.isPresent()) {
            Lobby lobby = lobbyOptional.get();
            lobby.removePlayer(playerId);
            return lobbyRepository.save(lobby);
        } else {
            throw new EntityNotFoundException("Lobby not found");
        }
    }
}
