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
        logger.info("Storing OTP '{}' for user '{}'", otp, userEmail);

        // Calculate expiry timestamp (e.g., 5 minutes from now)
        Instant expiryTimestamp = Instant.now().plus(Duration.ofMinutes(5));

        // Check if an OTP already exists for the user
        Optional<StoredOTP> existingOtpOptional = otpRepository.findByEmail(userEmail);
        StoredOTP storedOTP;

        if (existingOtpOptional.isPresent()) {
            // Update existing OTP
            storedOTP = existingOtpOptional.get();
            storedOTP.setOtp(otp);
            storedOTP.setExpiryTimestamp(expiryTimestamp);
            logger.info("OTP updated successfully for user {}: {}", userEmail, otp);
        } else {
            // Create a new StoredOTP object
            storedOTP = StoredOTP.builder()
                    .email(userEmail)
                    .otp(otp)
                    .expiryTimestamp(expiryTimestamp)
                    .build();
            logger.info("OTP created successfully for user {}: {}", userEmail, otp);
        }

        // Save the OTP to the database
        otpRepository.save(storedOTP);
    }

    @Override
    public String getStoredOTP(String userEmail) {
        Optional<StoredOTP> storedOTPOptional = otpRepository.findByEmail(userEmail);
        if (storedOTPOptional.isEmpty()) {
            logger.info("No OTP found for user {}", userEmail);
            return null; // No OTP found for the user email
        }

        StoredOTP storedOTP = storedOTPOptional.get();

        if (Instant.now().isAfter(storedOTP.getExpiryTimestamp())) {
            otpRepository.delete(storedOTP); // Remove expired OTP
            logger.info("Expired OTP removed for user {}", userEmail);
            return null; // Expired OTP
        }

        logger.info("OTP retrieved successfully for user {}: {}", userEmail, storedOTP.getOtp());
        return storedOTP.getOtp();
    }
}
