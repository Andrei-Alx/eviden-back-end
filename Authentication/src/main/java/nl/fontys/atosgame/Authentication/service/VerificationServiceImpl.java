package nl.fontys.atosgame.Authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationServiceImpl implements VerificationService {

    private final CodeStorageService codeStorageService; // You'll need to implement this service to store and retrieve OTPs

    @Autowired
    public VerificationServiceImpl(CodeStorageService codeStorageService) {
        this.codeStorageService = codeStorageService;
    }

    @Override
    public boolean verifyOTP(String userEmail, String enteredOTP) {
        // Retrieve the OTP associated with the user's email
        String storedOTP = codeStorageService.getStoredOTP(userEmail);
    
        // Log retrieved OTP
        System.out.println("Retrieved OTP for " + userEmail + ": " + storedOTP);
    
        // Check if the retrieved OTP is null
        if (storedOTP == null) {
            System.out.println("No OTP found for " + userEmail);
            return false; // Invalid OTP
        }
    
        // Check if the entered OTP matches the stored OTP
        boolean isOTPValid = storedOTP.equals(enteredOTP);
    
        // Log verification result
        System.out.println("Verification result for " + userEmail + ": " + isOTPValid);
    
        return isOTPValid;
    }
    
}