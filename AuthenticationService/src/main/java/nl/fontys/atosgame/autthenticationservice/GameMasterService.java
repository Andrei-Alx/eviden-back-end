package nl.fontys.atosgame.autthenticationservice;
import nl.fontys.atosgame.model.GameMaster;
import nl.fontys.atosgame.repository.GameMasterRepository;

public class GameMasterService {
        private GameMasterRepository gameMasterRepository;

    public boolean isGameMaster(String email) {
        GameMaster gameMaster = gameMasterRepository.findByEmail(email);
        return gameMaster != null;
    }
    
}
