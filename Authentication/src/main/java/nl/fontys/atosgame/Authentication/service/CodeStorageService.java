package nl.fontys.atosgame.Authentication.service;

public interface CodeStorageService {
    void storeOTP(String userEmail, String otp);
    String getStoredOTP(String userEmail);
}
