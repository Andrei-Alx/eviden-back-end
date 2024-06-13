package nl.fontys.atosgame.Authentication.service;

import nl.fontys.atosgame.Authentication.model.StoredOTP;
import nl.fontys.atosgame.Authentication.repository.OtpRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Service
public class CodeStorageServiceImpl implements CodeStorageService {
    private static final Logger logger = LoggerFactory.getLogger(CodeStorageServiceImpl.class);
    private final OtpRepository otpRepository;

    @Autowired
    public CodeStorageServiceImpl(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    @Override
    public void storeOTP(String userEmail, String otp) {
        // Log the code and email being stored
        logger.info("Storing OTP '{}' for user '{}'", otp, userEmail);

        // Calculate expiry timestamp (e.g., 5 minutes from now)
        Instant expiryTimestamp = Instant.now().plus(Duration.ofMinutes(5));

        // Create a new StoredOTP object
        StoredOTP storedOTP = StoredOTP.builder()
                .email(userEmail)
                .otp(otp)
                .expiryTimestamp(expiryTimestamp)
                .build();

        // Save the OTP to the database
        otpRepository.save(storedOTP);

        logger.info("OTP stored successfully for user {}: {}", userEmail, otp);
    }

    @Override
    public String getStoredOTP(String userEmail) {
        // Retrieve StoredOTP object associated with the user's email from the database
        Optional<StoredOTP> storedOTPOptional = otpRepository.findByEmail(userEmail);
        if (storedOTPOptional.isEmpty()) {
            logger.info("No OTP found for user {}", userEmail);
            return null; // No OTP found for the user email
        }

        StoredOTP storedOTP = storedOTPOptional.get();

        // Check if the OTP is expired
        if (Instant.now().isAfter(storedOTP.getExpiryTimestamp())) {
            otpRepository.delete(storedOTP); // Remove expired OTP
            logger.info("Expired OTP removed for user {}", userEmail);
            return null; // Expired OTP
        }

        logger.info("OTP retrieved successfully for user {}: {}", userEmail, storedOTP.getOtp());

        // Return the OTP if it's not expired
        return storedOTP.getOtp();
    }
}
