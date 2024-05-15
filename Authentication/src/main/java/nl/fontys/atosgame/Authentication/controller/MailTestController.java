package nl.fontys.atosgame.Authentication.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nl.fontys.atosgame.Authentication.service.MailSenderService;

@RestController
public class MailTestController {
    private final MailSenderService mailSenderService;

    public MailTestController(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @RequestMapping("/send-test-email")
    public String sendTestEmail() {
        String to = "evidendev@gmail.com";
        String subject = "Test Email";
        String body = "This is a test email sent from my Spring Boot application.";
        mailSenderService.sendNewMail(to, subject, body);
        return "Test email sent successfully!";
    }
}
