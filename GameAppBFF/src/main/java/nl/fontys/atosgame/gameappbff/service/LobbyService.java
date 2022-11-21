package nl.fontys.atosgame.gameappbff.service;

import nl.fontys.atosgame.gameappbff.controller.LobbyConsumers;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import org.springframework.stereotype.Service;

/**
 * Service for handling lobbies.
 */
public interface LobbyService {
    /**
     * Create a new lobby in the database.
     * @param lobby The lobby to create.
     * @return The created lobby.
     */
    Lobby createLobby(Lobby lobby);
}
