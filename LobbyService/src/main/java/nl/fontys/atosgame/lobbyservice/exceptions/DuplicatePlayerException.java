package nl.fontys.atosgame.lobbyservice.exceptions;

public class DuplicatePlayerException extends Exception {

    public DuplicatePlayerException(String errorMessage) {
        super(errorMessage);
    }
}
