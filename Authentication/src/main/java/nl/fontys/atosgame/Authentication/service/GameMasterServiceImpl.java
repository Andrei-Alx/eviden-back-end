package nl.fontys.atosgame.Authentication.service;

import nl.fontys.atosgame.Authentication.model.GameMaster;
import nl.fontys.atosgame.Authentication.repository.GameMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameMasterServiceImpl implements GameMasterService {

    private final GameMasterRepository gameMasterRepository;

    @Autowired
    public GameMasterServiceImpl(GameMasterRepository gameMasterRepository) {
        this.gameMasterRepository = gameMasterRepository;
    }

    @Override
    public GameMaster saveGameMaster(GameMaster gameMaster) {
        return gameMasterRepository.save(gameMaster);
    }

    @Override
    public GameMaster findGameMasterById(Long id) {
        return gameMasterRepository.findById(id).orElse(null);
    }

    @Override
    public GameMaster findGameMasterByEmail(String email) {
        return gameMasterRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return gameMasterRepository.findByEmail(email) != null;
    }
}
