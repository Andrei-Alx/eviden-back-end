package nl.fontys.atosgame.Authentication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class CodeStorageServiceImpl implements CodeStorageService {

    private final Map<String, StoredOTP> otpStorage = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(CodeStorageServiceImpl.class);

    @Override
    public void storeOTP(String userEmail, String otp) {
        // Log the code and email being stored
        logger.info("Storing OTP '{}' for user '{}'", otp, userEmail);
        System.out.println("Storing OTP '" + otp + "' for user '" + userEmail + "'");
    
        // Calculate expiry timestamp (e.g., 5 minutes from now)
        Instant expiryTimestamp = Instant.now().plus(Duration.ofMinutes(5));
    
        // Store OTP and expiry timestamp in the map
        otpStorage.put(userEmail, new StoredOTP(otp, expiryTimestamp));
    
        logger.info("OTP stored successfully for user {}: {}", userEmail, otp);
        System.out.println("OTP stored successfully for user " + userEmail + ": " + otp);
    }

    @Override
    public String getStoredOTP(String userEmail) {
        // Retrieve StoredOTP object associated with the user's email
        StoredOTP storedOTP = otpStorage.get(userEmail);
        if (storedOTP == null) {
            logger.info("No OTP found for user {}", userEmail);
            System.out.println("No OTP found for user " + userEmail);
            return null; // No OTP found for the user email
        }

        // Check if the OTP is expired
        if (Instant.now().isAfter(storedOTP.getExpiryTimestamp())) {
            otpStorage.remove(userEmail); // Remove expired OTP
            logger.info("Expired OTP removed for user {}", userEmail);
            System.out.println("Expired OTP removed for user " + userEmail);
            return null; // Expired OTP
        }

        logger.info("OTP retrieved successfully for user {}: {}", userEmail, storedOTP.getOTP());
        System.out.println("OTP retrieved successfully for user " + userEmail + ": " + storedOTP.getOTP());

        // Return the OTP if it's not expired
        return storedOTP.getOTP();
    }

    // Define a class to encapsulate OTP and expiry timestamp
    private static class StoredOTP {
        private final String otp;
        private final Instant expiryTimestamp;

        public StoredOTP(String otp, Instant expiryTimestamp) {
            this.otp = otp;
            this.expiryTimestamp = expiryTimestamp;
        }

        public String getOTP() {
            return otp;
        }

        public Instant getExpiryTimestamp() {
            return expiryTimestamp;
        }
    }
}
