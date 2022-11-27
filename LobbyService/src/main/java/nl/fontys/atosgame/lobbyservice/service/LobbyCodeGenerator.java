package nl.fontys.atosgame.lobbyservice.service;

/**
 * Generates a lobby code.
 */
public interface LobbyCodeGenerator {
    /**
     * Generates a lobby code.
     * @return The generated lobby code.
     */
    String generateLobbyCode();
}
