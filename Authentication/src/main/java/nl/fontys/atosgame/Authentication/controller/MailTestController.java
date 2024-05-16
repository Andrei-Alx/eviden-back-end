package nl.fontys.atosgame.Authentication.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import nl.fontys.atosgame.Authentication.service.MailSenderService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@RestController
@RequestMapping("/api/gamemaster")
@CrossOrigin(origins = "*")
public class MailTestController {

    private static final Logger logger = LoggerFactory.getLogger(MailTestController.class);
    private final MailSenderService mailSenderService;

    public MailTestController(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @RequestMapping("/send-test-email")
    public String sendTestEmail() {
        logger.info("endpoint hit: /api/gamemaster/send-test-email");
        String to = "j.hooftman2k@gmail.com";
        String subject = "Test Email";
        String body = "This is a test email sent from my Spring Boot application.";
        mailSenderService.sendNewMail(to, subject, body);
        return "Test email sent successfully!";
    }
}
