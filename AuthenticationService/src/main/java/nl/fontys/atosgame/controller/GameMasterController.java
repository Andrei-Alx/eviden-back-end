package nl.fontys.atosgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import nl.fontys.atosgame.service.GameMasterService;

public class GameMasterController {
        @Autowired
    private GameMasterService gameMasterService;

    @GetMapping("/checkGameMaster/{email}")
    public String checkGameMaster(@PathVariable String email) {
        boolean isGameMaster = gameMasterService.isGameMaster(email);
        if (isGameMaster) {
            return "This email belongs to a game master.";
        } else {
            return "This email does not belong to a game master.";
        }
    }
}
