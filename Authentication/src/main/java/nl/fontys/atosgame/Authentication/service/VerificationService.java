package nl.fontys.atosgame.Authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import java.util.Map;
import java.util.HashMap;
import org.springframework.mail.MailException;

@Service
public class VerificationService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private CodeGeneratorService codeGeneratorService;

    // Store email and code temporarily for verification
    private Map<String, String> codeStorage = new HashMap<>();

    public void sendVerificationCode(String emailAddress) {
        // Generate a random code
        String code = codeGeneratorService.generateRandomCode(6);
        // Store the code with the email address
        codeStorage.put(emailAddress, code);
        // Send the code via email
        try {
            emailService.sendCodeByEmail(emailAddress, "Verification Code", "Your verification code is: " + code);
        } catch ( MailException e) {
            // Handle email sending exception
            e.printStackTrace();
        }
    }

    public boolean verifyCode(String emailAddress, String enteredCode) {
        // Retrieve the stored code for the email address
        String storedCode = codeStorage.get(emailAddress);
        // Check if the entered code matches the stored code
        return storedCode != null && storedCode.equals(enteredCode);
    }
}
