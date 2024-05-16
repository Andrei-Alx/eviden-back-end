package nl.fontys.atosgame.Authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import nl.fontys.atosgame.Authentication.service.VerificationService;

@RestController
@RequestMapping("/api/gamemaster")
@CrossOrigin(origins = "*")
public class VerificationController {

    private final VerificationService verificationService;
  
    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @PostMapping("/verify-otp")
    public String verifyOTP(@RequestParam String userEmail, @RequestParam String enteredOTP) {
        // Verify OTP
        boolean isOTPValid = verificationService.verifyOTP(userEmail, enteredOTP);
        
        if (isOTPValid) {
            return "OTP verified successfully!";
        } else {
            return "Invalid OTP!";
        }
    }
}
