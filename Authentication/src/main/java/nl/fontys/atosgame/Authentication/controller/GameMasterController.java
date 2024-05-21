package nl.fontys.atosgame.Authentication.controller;

import nl.fontys.atosgame.Authentication.model.GameMaster;
import nl.fontys.atosgame.Authentication.service.GameMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gamemaster")
public class GameMasterController {

    @Autowired
    private GameMasterService gameMasterService;

    @GetMapping("/checkGameMaster/{email}")
    public String checkGameMaster(@PathVariable String email) {
        boolean isGameMaster = gameMasterService.existsByEmail(email);
        if (isGameMaster) {
            return "This email belongs to a game master.";
        } else {
            return "This email does not belong to a game master.";
        }
    }

    @GetMapping("/findByEmail/{email}")
    public GameMaster findGameMasterByEmail(@PathVariable String email) {
        return gameMasterService.findGameMasterByEmail(email);
    }

    @PostMapping("/add")
    public GameMaster addGameMaster(@RequestBody GameMaster gameMaster) {
        return gameMasterService.saveGameMaster(gameMaster);
    }

    @GetMapping("/api/example")
    public String displayData() {
        return "Welcome to GeeksForGeeks";
    }
}
