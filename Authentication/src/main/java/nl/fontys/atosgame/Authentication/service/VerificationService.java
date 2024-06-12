package nl.fontys.atosgame.Authentication.service;

public interface VerificationService {
    boolean verifyOTP(String userEmail, String enteredOTP);
}
