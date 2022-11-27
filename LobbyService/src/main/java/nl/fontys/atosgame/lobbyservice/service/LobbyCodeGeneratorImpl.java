package nl.fontys.atosgame.lobbyservice.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

/**
 * Generates a lobby code.
 *
 * @author Eli
 */
@Service
public class LobbyCodeGeneratorImpl implements LobbyCodeGenerator {

    /**
     * Generates a lobby code.
     *
     * @return The generated lobby code.
     */
    @Override
    public String generateLobbyCode() {
        return RandomStringUtils.random(6, true, false).toUpperCase();
    }
}
