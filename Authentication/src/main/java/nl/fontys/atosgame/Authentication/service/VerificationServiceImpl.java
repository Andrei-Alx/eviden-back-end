package nl.fontys.atosgame.Authentication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationServiceImpl implements VerificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VerificationServiceImpl.class);
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
        LOGGER.info("Retrieved OTP for {}: {}", userEmail, storedOTP);

        // Check if the retrieved OTP is null
        if (storedOTP == null) {
            LOGGER.info("No OTP found for {}", userEmail);
            return false; // Invalid OTP
        }
    
        // Check if the entered OTP matches the stored OTP
        boolean isOTPValid = storedOTP.equals(enteredOTP);
    
        // Log verification result
        LOGGER.info("OTP verified: {}", isOTPValid);
    
        return isOTPValid;
    }
    
}
