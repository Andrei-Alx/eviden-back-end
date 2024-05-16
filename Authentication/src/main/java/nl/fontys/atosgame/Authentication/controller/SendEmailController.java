package nl.fontys.atosgame.Authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import nl.fontys.atosgame.Authentication.service.EmailService;
import nl.fontys.atosgame.Authentication.service.CodeGeneratorService;
import nl.fontys.atosgame.Authentication.service.CodeStorageService;
import org.slf4j.LoggerFactory;
import org.aspectj.apache.bcel.classfile.Code;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gamemaster")
@CrossOrigin(origins = "*")
public class SendEmailController {
    private final EmailService emailService;
   

    private static final Logger logger = LoggerFactory.getLogger(SendEmailController.class);
  
    public SendEmailController(EmailService emailService) {
        this.emailService = emailService;
 
    }

    @PostMapping("/send-otp")
    public String sendOTP(@RequestParam String userEmail) {
        logger.info("Endpoint hit: /api/gamemaster/send-otp");
        
        // Send OTP to the user's email
        emailService.sendOTPByEmail(userEmail);
        
        return "OTP sent successfully!";
    }
}
