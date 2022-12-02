package nl.fontys.atosgame.roundservice.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.model.Game;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import nl.fontys.atosgame.roundservice.model.Round;
import nl.fontys.atosgame.roundservice.service.GameService;
import nl.fontys.atosgame.roundservice.service.PlayerRoundService;
import nl.fontys.atosgame.roundservice.service.RoundService;
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
public class TestController {

    @Autowired
    private RoundService roundService;

    @Autowired
    private GameService gameService;

    @GetMapping("/test")
    public Game start(@RequestBody UUID gameId) {
        return gameService.startGame(gameId);
    }

    @GetMapping("/selected")
    public Round test(@RequestBody TestDto dto) {
        List<UUID> cardIds = dto.cardIds
            .stream()
            .map(UUID::fromString)
            .collect(Collectors.toList());
        return roundService.selectCards(
            UUID.fromString(dto.playerId),
            cardIds,
            UUID.fromString(dto.gameId),
            UUID.fromString(dto.roundId)
        );
    }
}
