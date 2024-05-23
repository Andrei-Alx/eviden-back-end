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
    
    public void deleteAllGameMasters() {
        gameMasterRepository.deleteAll();
    }

    @Override
    public GameMaster findGameMasterByEmail(String email) {
        return gameMasterRepository.findByEmail(email);
    }

    @Override
    public GameMaster saveGameMaster(GameMaster gameMaster) {
        return gameMasterRepository.save(gameMaster);
    }

    @Override
    public List<GameMaster> findAllGameMasters() {
        return gameMasterRepository.findAll();
    }

    @Override
    public void storeOtp(String email, String otp) {
        codeStorageService.storeOTP(email, otp);
    }

    @Override
    public String getStoredOtp(String email) {
        return codeStorageService.getStoredOTP(email);
    }

    @Override
    public boolean verifyOtp(String email, String otp) {
        String storedOtp = getStoredOtp(email);
        return storedOtp != null && storedOtp.equals(otp);
    }
    
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
