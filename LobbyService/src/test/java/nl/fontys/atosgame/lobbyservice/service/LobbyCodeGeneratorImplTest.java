package nl.fontys.atosgame.lobbyservice.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LobbyCodeGeneratorImplTest {

    @Test
    void generateLobbyCode() {
        LobbyCodeGeneratorImpl lobbyCodeGenerator = new LobbyCodeGeneratorImpl();
        String lobbyCode = lobbyCodeGenerator.generateLobbyCode();
        assertEquals(6, lobbyCode.length());
        assertTrue(lobbyCode.matches("[A-Z]+"));
    }
}
