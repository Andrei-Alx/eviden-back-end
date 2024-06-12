package nl.fontys.atosgame.Authentication.service;

public interface CodeGeneratorService {
    String generateRandomOTP(int length);
}
