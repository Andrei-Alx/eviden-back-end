package nl.fontys.atosgame.lobbyservice.exceptions;

public class EmptyPlayerNameException extends Exception {

    public EmptyPlayerNameException(String errorMessage) {
        super(errorMessage);
    }
}
