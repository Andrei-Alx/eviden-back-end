package nl.fontys.atosgame.roundservice.controller;

import java.util.Optional;
import java.util.UUID;

import nl.fontys.atosgame.roundservice.model.Game;
import nl.fontys.atosgame.roundservice.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test controller to test internal functions
 * REMOVE
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public Optional<Game> test(@RequestBody UUID roundId) {
        return gameService.getGameByRoundId(roundId);
    }
}
