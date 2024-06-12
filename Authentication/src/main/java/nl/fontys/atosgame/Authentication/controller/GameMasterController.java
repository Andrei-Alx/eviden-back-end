package nl.fontys.atosgame.Authentication.controller;
import nl.fontys.atosgame.Authentication.model.GameMaster;
import nl.fontys.atosgame.Authentication.service.GameMasterService;
import nl.fontys.atosgame.Authentication.service.EmailService;
import nl.fontys.atosgame.Authentication.service.CustomUserDetailsService;
import nl.fontys.atosgame.Authentication.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
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
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @DeleteMapping("/deleteAll")
    public String deleteAllGameMasters() {
        gameMasterService.deleteAllGameMasters();
        return "All GameMaster records have been deleted.";
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<?> findGameMasterByEmail(@RequestParam String email) {
        GameMaster gameMaster = gameMasterService.findGameMasterByEmail(email);
        if (gameMaster != null) {
            return ResponseEntity.ok(gameMaster);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                               .body("No game master found with email: " + email);
        }
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
        List<GameMaster> gameMasters = gameMasterService.findAllGameMasters();
        if (gameMasters.isEmpty()) {
            throw new RuntimeException("No game masters found.");
        }
        return gameMasters;
    }

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

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String email, @RequestParam String otp) {
        boolean isVerified = gameMasterService.verifyOtp(email, otp);
        Map<String, String> response = new HashMap<>();
        if (isVerified) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
            String token = jwtUtil.generateToken(userDetails.getUsername());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid OTP or OTP has expired.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
