package nl.fontys.atosgame.Authentication.service;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class CodeGeneratorServiceImpl implements CodeGeneratorService {
    
    @Override
    public String generateRandomOTP(int length) {
        // Define the characters allowed in the OTP
        String allowedChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder otp = new StringBuilder();

        // Create a random object
        Random random = new Random();

        // Generate the OTP of the specified length
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            otp.append(allowedChars.charAt(randomIndex));
        }

        return otp.toString();
    }
}
