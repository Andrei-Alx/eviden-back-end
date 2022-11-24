package nl.fontys.atosgame.lobbyservice.service;

import java.util.*;

import kotlin.collections.UCollectionsKt;
import nl.fontys.atosgame.lobbyservice.CustomException.FullLobbyException;
import nl.fontys.atosgame.lobbyservice.dto.LobbyDeletedDto;
import nl.fontys.atosgame.lobbyservice.dto.LobbyJoinedDto;
import nl.fontys.atosgame.lobbyservice.dto.LobbyQuitDto;
import nl.fontys.atosgame.lobbyservice.model.Lobby;
import nl.fontys.atosgame.lobbyservice.model.LobbySettings;
import nl.fontys.atosgame.lobbyservice.model.Player;
import nl.fontys.atosgame.lobbyservice.repository.LobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * Service for handling lobbies.
 *
 * @author Eli
 */
@Service
public class LobbyServiceImpl implements LobbyService {

    private final LobbyRepository lobbyRepository;

    private final LobbyCodeGenerator lobbyCodeGenerator;

    private final StreamBridge streamBridge;

    public LobbyServiceImpl(
            @Autowired LobbyRepository lobbyRepository,
            @Autowired LobbyCodeGenerator lobbyCodeGenerator,
            @Autowired StreamBridge streamBridge
    ) {
        this.lobbyRepository = lobbyRepository;
        this.lobbyCodeGenerator = lobbyCodeGenerator;
        this.streamBridge = streamBridge;
    }

    /**
     * Creates a new lobby.
     *
     * @param settings The settings for the lobby.
     * @param gameId   The id of the game.
     * @return The created lobby.
     */
    @Override
    public Lobby createLobby(LobbySettings settings, UUID gameId) {
        Lobby lobby = new Lobby();
        lobby.setLobbySettings(settings);
        lobby.setGameId(gameId);
        lobby.setLobbyCode(lobbyCodeGenerator.generateLobbyCode());
        Lobby createdLobby = lobbyRepository.save(lobby);
        streamBridge.send("produceLobbyCreated-in-0", createdLobby);
        return lobbyRepository.save(lobby);
    }

    /**
     * Deletes a lobby by gameId.
     *
     * @param gameId The gameid of the lobby.
     */
    @Override
    public void deleteLobbyByGameId(UUID gameId) {
        UUID lobbyId = lobbyRepository.getByGameId(gameId).getId();
        lobbyRepository.deleteByGameId(gameId);
        streamBridge.send(
                "produceLobbyDeleted-in-0",
                new LobbyDeletedDto(lobbyId, gameId)
        );
    }

    /**
     * R-7
     * this method adds a player to the lobby and produces lobby joined event
     * @param lobbyCode
     * @param playerName
     * @return
     * @throws Exception
     */
    @Override
    public Lobby joinLobby(String lobbyCode, String playerName) throws Exception {
        //get lobby to edit
        Lobby lobby = lobbyRepository.getByLobbyCode(lobbyCode);
        if(lobby == null){throw new EntityNotFoundException("No lobby found for lobbycode"+ lobbyCode);}
        //check whether the lobby is already full
        if (lobby.getPlayers().size() >= lobby.getLobbySettings().getMaxPlayers()) { throw new FullLobbyException("Maximum number of players in lobby reached");
        }
        //add player
        Player player = new Player();
        player.setName(playerName);
        player.setId(UUID.randomUUID());
        lobby.getPlayers().add(player);

        //point of no return
        lobbyRepository.saveAndFlush(lobby);

        LobbyJoinedDto lobbyJoinedDto = new LobbyJoinedDto(lobby.getId(), lobby.getPlayers(), player.getId(), lobby.getLobbyCode(), lobby.getGameId());
        streamBridge.send("producePlayerJoined-in-0", lobbyJoinedDto);
        return lobby;
    }

    /**
     * R-8
     * This method removes a player from the lobby
     * @param lobbyId
     * @param playerId
     */
    @Override
    public void quitLobby(UUID lobbyId, UUID playerId){
        Lobby lobby = lobbyRepository.getById(lobbyId);
        if(lobby == null){throw new EntityNotFoundException("No lobby found for lobbyId"+ lobbyId);}
        lobby.getPlayers().remove(lobby.getPlayers().stream().filter(player -> player.getId() == playerId).findFirst().orElse(null));

        lobbyRepository.saveAndFlush(lobby);

        LobbyQuitDto lobbyQuitDto = new LobbyQuitDto(lobbyId, playerId, lobby.getGameId());
        streamBridge.send("producePlayerQuit-in-0", lobbyQuitDto);
    }
}
