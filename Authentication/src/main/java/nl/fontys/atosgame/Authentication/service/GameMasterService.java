package nl.fontys.atosgame.Authentication.service;

import nl.fontys.atosgame.Authentication.model.GameMaster;

public interface GameMasterService {
    GameMaster saveGameMaster(GameMaster gameMaster);
    GameMaster findGameMasterById(Long id);
    GameMaster findGameMasterByEmail(String email);
    boolean existsByEmail(String email);
}