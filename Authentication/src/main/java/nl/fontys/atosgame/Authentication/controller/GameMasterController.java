package nl.fontys.atosgame.Authentication.controller;

import nl.fontys.atosgame.Authentication.model.GameMaster;
import nl.fontys.atosgame.Authentication.service.GameMasterService;
import nl.fontys.atosgame.Authentication.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/gamemaster")
@CrossOrigin(origins = "*")
public class GameMasterController {

    @Autowired
    private GameMasterService gameMasterService;

    @Autowired
    private EmailService emailService;

    // Delete all game masters
    @DeleteMapping("/deleteAll")
    public String deleteAllGameMasters() {
        gameMasterService.deleteAllGameMasters();
        return "All GameMaster records have been deleted.";
    }


    // Find game master by email
    @GetMapping("/findByEmail")
    public ResponseEntity<?> findGameMasterByEmail(@RequestParam String email) {
        System.out.println("Email: " + email);
        GameMaster gameMaster = gameMasterService.findGameMasterByEmail(email);
        if (gameMaster != null) {
            return ResponseEntity.ok(gameMaster);
        } else {
            // Return a custom response with status 404 and a custom message
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                               .body("No game master found with email: " + email);
        }
    }
    // Add a new game master
    @PostMapping("/add")
    public GameMaster addGameMaster(@RequestBody GameMaster gameMaster) {
        return gameMasterService.saveGameMaster(gameMaster);
    }

    // Delete game master by email
    @DeleteMapping("/deleteByEmail/{email}")
    public String deleteGameMasterByEmail(@PathVariable String email) {
        boolean deleted = gameMasterService.deleteGameMasterByEmail(email);
        if (deleted) {
            return "GameMaster with email " + email + " deleted successfully.";
        } else {
            return "GameMaster with email " + email + " not found.";
        }
    }

    // Get all game masters
    @GetMapping("/all")
    public List<GameMaster> getAllGameMasters() {
        List<GameMaster> gameMasters = gameMasterService.findAllGameMasters();
        if (gameMasters.isEmpty()) {
            throw new RuntimeException("No game masters found.");
        }
        return gameMasters;
    }

    // Generate OTP for a game master
    @PostMapping("/generateOtp")
    public ResponseEntity<Map<String, String>> generateOtp(@RequestParam String email) {
        GameMaster gameMaster = gameMasterService.findGameMasterByEmail(email);
        Map<String, String> response = new HashMap<>();
        if (gameMaster != null) {
            emailService.sendOTPByEmail(email);
            response.put("message", "OTP has been sent to your email.");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "This email does not belong to a game master.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    // Verify OTP for a game master
    @PostMapping("/verifyOtp")
    public ResponseEntity<Map<String, String>> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isVerified = gameMasterService.verifyOtp(email, otp);
        Map<String, String> response = new HashMap<>();
        if (isVerified) {
            response.put("message", "OTP verified successfully. You are logged in.");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid OTP or OTP has expired.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


}
