package nl.fontys.atosgame.Authentication.service;

import org.springframework.stereotype.Service;
import java.util.Random;
import java.security.SecureRandom;

@Service
public class CodeGeneratorServiceImpl implements CodeGeneratorService {
    
    @Override
    public String generateRandomOTP(int length) {
        // Define the characters allowed in the OTP
        String allowedChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder otp = new StringBuilder();

        // Create a random object
       // Random random = new Random();
        SecureRandom secureRandom = new SecureRandom();

        // Generate the OTP of the specified length
        for (int i = 0; i < length; i++) {
            //int randomIndex = random.nextInt(allowedChars.length());
            int randomSecureIndex = secureRandom.nextInt(allowedChars.length());
            //otp.append(allowedChars.charAt(randomIndex));
            otp.append(secureRandom.nextInt(randomSecureIndex));   // Add a random number between 0 and 9
        }

        return otp.toString();

    }
}
