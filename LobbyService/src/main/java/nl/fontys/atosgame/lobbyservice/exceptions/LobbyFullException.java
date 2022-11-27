package nl.fontys.atosgame.lobbyservice.exceptions;

public class LobbyFullException extends Exception {
    public LobbyFullException(String errorMessage) {
        super(errorMessage);
    }
}
