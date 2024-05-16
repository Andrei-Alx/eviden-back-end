package nl.fontys.atosgame.Authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final CodeGeneratorService codeGeneratorService;
    private final CodeStorageService codeStorageService;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender, CodeGeneratorService codeGeneratorService, CodeStorageService codeStorageService) {
        this.javaMailSender = javaMailSender;
        this.codeGeneratorService = codeGeneratorService;
        this.codeStorageService = codeStorageService;
    }

    @Override
    public void sendOTPByEmail(String toEmailAddress) {
        // Generate OTP
        String otp = codeGeneratorService.generateRandomOTP(6); // Generate a 6-digit OTP
        codeStorageService.storeOTP(toEmailAddress, otp); // Store the OTP with a 5-minute expiry duration

        // Compose email body with OTP and expiry duration
        String body = "Hi Game Master,\n\n"
                    + "Here is your Login Code. This code will expire in 5 minutes.\n\n"
                    + otp + "\n\n"
                    + "Kind regards,\n"
                    + "Eviden";

        // Send email
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmailAddress);
            message.setSubject("Your Login Code");
            message.setText(body);
            javaMailSender.send(message);
            System.out.println("OTP sent successfully.");
        } catch (MailException e) {
            System.out.println("Failed to send OTP email: " + e.getMessage());
        }
    }
}
