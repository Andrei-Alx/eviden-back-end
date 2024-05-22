package nl.fontys.atosgame.Authentication.controller;

import nl.fontys.atosgame.Authentication.model.GameMaster;
import nl.fontys.atosgame.Authentication.service.GameMasterService;
import nl.fontys.atosgame.Authentication.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gamemaster")
public class GameMasterController {

    @Autowired
    private GameMasterService gameMasterService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/findByEmail")
    public GameMaster findGameMasterByEmail(@RequestParam String email) {
        return gameMasterService.findGameMasterByEmail(email);
    }

    @PostMapping("/add")
    public GameMaster addGameMaster(@RequestBody GameMaster gameMaster) {
        return gameMasterService.saveGameMaster(gameMaster);
    }

    
    @DeleteMapping("/deleteByEmail/{email}")
    public String deleteGameMasterByEmail(@PathVariable String email) {
        boolean deleted = gameMasterService.deleteGameMasterByEmail(email);
        if (deleted) {
            return "GameMaster with email " + email + " deleted successfully.";
        } else {
            return "GameMaster with email " + email + " not found.";
        }
    }

    @GetMapping("/all")
    public List<GameMaster> getAllGameMasters() {
        return gameMasterService.findAllGameMasters();
    }

    @PostMapping("/generateOtp")
    public String generateOtp(@RequestParam String email) {
        GameMaster gameMaster = gameMasterService.findGameMasterByEmail(email);
        if (gameMaster != null) {
            emailService.sendOTPByEmail(email);
            
            return "OTP has been sent to your email.";
        } else {
            return "This email does not belong to a game master.";
        }
    }
 

    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isVerified = gameMasterService.verifyOtp(email, otp);
        if (isVerified) {
            return "OTP verified successfully. You are logged in.";
        } else {
            return "Invalid OTP or OTP has expired.";
        }
    }

    @GetMapping("/api/example")
    public String displayData() {
        return "Welcome to GeeksForGeeks";
    }
}
