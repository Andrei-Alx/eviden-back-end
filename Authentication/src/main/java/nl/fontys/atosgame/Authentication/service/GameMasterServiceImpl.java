package nl.fontys.atosgame.Authentication.service;

import nl.fontys.atosgame.Authentication.model.GameMaster;
import nl.fontys.atosgame.Authentication.repository.GameMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameMasterServiceImpl implements GameMasterService {

    @Autowired
    private GameMasterRepository gameMasterRepository;

    @Autowired
    private CodeStorageServiceImpl codeStorageService;

    // Delete all game masters
    @Override
    public void deleteAllGameMasters() {
        gameMasterRepository.deleteAll();
    }

    // Find game master by email
    @Override
    public GameMaster findGameMasterByEmail(String email) {
        return gameMasterRepository.findByEmail(email);
    }

    // Save game master
    @Override
    public GameMaster saveGameMaster(GameMaster gameMaster) {
        return gameMasterRepository.save(gameMaster);
    }

    // Find all game masters
    @Override
    public List<GameMaster> findAllGameMasters() {
        List<GameMaster> gameMasters = gameMasterRepository.findAll();
        if (gameMasters.isEmpty()) {
            throw new RuntimeException("No game masters found.");
        }
        return gameMasters;
    }

    // Store OTP for a game master
    @Override
    public void storeOtp(String email, String otp) {
        codeStorageService.storeOTP(email, otp);
    }

    // Get stored OTP for a game master
    @Override
    public String getStoredOtp(String email) {
        return codeStorageService.getStoredOTP(email);
    }

    // Verify OTP for a game master
    @Override
    public boolean verifyOtp(String email, String otp) {
        String storedOtp = getStoredOtp(email);
        return storedOtp != null && storedOtp.equals(otp);
    }

    // Delete game master by email
    @Override
    public boolean deleteGameMasterByEmail(String email) {
        GameMaster gameMaster = gameMasterRepository.findByEmail(email);
        if (gameMaster != null) {
            gameMasterRepository.delete(gameMaster);
            return true; // Deletion successful
        }
        return false; // GameMaster with specified email not found
    }
}
