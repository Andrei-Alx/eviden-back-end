package nl.fontys.atosgame.service;
import nl.fontys.atosgame.model.GameMaster;
import nl.fontys.atosgame.repository.GameMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class GameMasterService {
        private GameMasterRepository gameMasterRepository;


    @Autowired
    public GameMasterService(GameMasterRepository gameMasterRepository) {
        this.gameMasterRepository = gameMasterRepository;
    }

    public GameMaster saveGameMaster(GameMaster gameMaster) {
        return gameMasterRepository.save(gameMaster);
    }

    public GameMaster findGameMasterById(Long id) {
        return gameMasterRepository.findById(id).orElse(null);
    }
        
    public boolean isGameMaster(String email) {
        GameMaster gameMaster = gameMasterRepository.findByEmail(email);
        return gameMaster != null;
    }
    
}
