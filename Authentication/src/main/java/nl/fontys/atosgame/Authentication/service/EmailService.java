package nl.fontys.atosgame.Authentication.service;

public interface EmailService {
    void sendOTPByEmail(String toEmailAddress);
}
