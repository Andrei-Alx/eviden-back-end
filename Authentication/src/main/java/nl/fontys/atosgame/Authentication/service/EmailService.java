package nl.fontys.atosgame.Authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendCodeByEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
    // @Autowired
    // private final JavaMailSender javaMailSender;

    
    // public EmailService(JavaMailSender javaMailSender) {
    //     this.javaMailSender = javaMailSender;
        
    // }

    // public void sendCodeByEmail(String toEmailAddress, String subject, String bodyText) {
    //     SimpleMailMessage message = new SimpleMailMessage();
    //     message.setTo(toEmailAddress);
    //     message.setSubject(subject);
    //     message.setText(bodyText);

    //     try {
    //         javaMailSender.send(message);
    //         System.out.println("Email sent successfully.");
    //     } catch (MailException e) {
    //         System.out.println("Failed to send email: " + e.getMessage());
    //     }
    // }
}
