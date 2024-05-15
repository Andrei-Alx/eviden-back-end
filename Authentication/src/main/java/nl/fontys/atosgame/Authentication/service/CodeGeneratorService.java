package nl.fontys.atosgame.Authentication.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CodeGeneratorService {
    
    public String generateRandomCode(int length) {
        // Define the characters allowed in the code
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();

        // Create a random object
        Random random = new Random();

        // Generate the code of the specified length
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            code.append(allowedChars.charAt(randomIndex));
        }

        return code.toString();

    }

    
}
