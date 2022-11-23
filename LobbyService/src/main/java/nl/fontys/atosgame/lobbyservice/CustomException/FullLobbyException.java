package nl.fontys.atosgame.lobbyservice.CustomException;

public class FullLobbyException extends Exception {
    public FullLobbyException(String errorMessage) {
        super(errorMessage);
    }
}
