package nl.fontys.atosgame.Authentication.service;

import nl.fontys.atosgame.Authentication.model.GameMaster;

import java.util.List;

public interface GameMasterService {

    GameMaster findGameMasterByEmail(String email);

    GameMaster saveGameMaster(GameMaster gameMaster);

    List<GameMaster> findAllGameMasters();

    void storeOtp(String email, String otp);

    String getStoredOtp(String email);

    boolean verifyOtp(String email, String otp);
    boolean deleteGameMasterByEmail(String email);
}
