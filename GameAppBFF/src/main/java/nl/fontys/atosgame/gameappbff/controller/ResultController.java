package nl.fontys.atosgame.gameappbff.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.gameappbff.dto.PlayerDto;
import nl.fontys.atosgame.gameappbff.dto.ResultCardDto;
import nl.fontys.atosgame.gameappbff.dto.ResultDto;
import nl.fontys.atosgame.gameappbff.enums.ShowResults;
import nl.fontys.atosgame.gameappbff.model.*;
import nl.fontys.atosgame.gameappbff.service.GameService;
import nl.fontys.atosgame.gameappbff.service.LobbyService;
import nl.fontys.atosgame.gameappbff.service.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for the results.
 *
 * @author Niek and Kevin
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/results")
public class ResultController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultController.class);
    private final ResultService resultService;

    private final GameService gameService;

    private ResultController(
        @Autowired ResultService resultService,
        @Autowired GameService gameService
    ) {
        this.resultService = resultService;
        this.gameService = gameService;
    }

    /**
     * Id: R-10
     * Get player round results.
     *
     * @return
     */
    @GetMapping("/phase3")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Getting a player round results ",
                content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Player round results not found"
            ),
        }
    )
    public ResponseEntity<List<ResultDto>> getPlayerRoundResult(
        @RequestParam UUID roundId,
        @RequestParam UUID gameId
    ) {
        LOGGER.info(String.format("get player round results get request (result controller) => %s", roundId));
        Optional<List<PlayerRoundResult>> playerResults = resultService.getPlayerRoundResults(
            roundId
        );

        // get player
        List<Player> players = new ArrayList<>(
            gameService.getGame(gameId).get().getLobby().getPlayers()
        );
        List<ResultDto> results = new ArrayList<>();
        for (PlayerRoundResult playerRoundResult : playerResults.get()) {
            // get player from players list with playerRoundResult playerId
            PlayerDto playerDto = new PlayerDto(
                players
                    .stream()
                    .filter(player ->
                        player.getId().equals(playerRoundResult.getPlayerId())
                    )
                    .findFirst()
                    .get()
                    .getName()
            );

            // get the showResults
            ShowResults showResults = playerRoundResult.getResult().getType();

            // get the results
            List<String> playerRoundResults = playerRoundResult.getResult().getResult();

            // get the chosen cards
            List<Card> chosenCards = playerRoundResult.getResult().getChosenCards();
            // convert to ResultCardDto
            List<ResultCardDto> chosenCardsDto = new ArrayList<>();
            for (Card card : chosenCards) {
                for (Translation translation : card.getTranslations()) {
                    chosenCardsDto.add(
                        new ResultCardDto(
                            card.getTags().stream().findFirst().get().getTagValue(),
                            translation.getText(),
                            translation.getLanguage()
                        )
                    );
                }
            }

            // get the advice cards
            List<Card> adviceCards = playerRoundResult.getResult().getAdviceCards();
            List<ResultCardDto> adviceCardsDto = new ArrayList<>();
            for (Card card : adviceCards) {
                for (Translation translation : card.getTranslations()) {
                    adviceCardsDto.add(
                        new ResultCardDto(
                            card.getTags().stream().findFirst().get().getTagValue(),
                            translation.getText(),
                            translation.getLanguage()
                        )
                    );
                }
            }

            results.add(
                Optional
                    .of(
                        new ResultDto(
                            playerDto,
                            showResults,
                            playerRoundResults,
                            chosenCardsDto,
                            adviceCardsDto
                        )
                    )
                    .get()
            );
        }

        return ResponseEntity.ok(results);
    }


    @GetMapping("/phase3individual")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Getting a player round results (individual)",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Player round results not found"
                    ),
            }
    )
    public ResponseEntity <ResultDto> getPlayerRoundResult(
            @RequestParam UUID roundId,
            @RequestParam UUID gameId,
            @RequestParam UUID playerId
    )
    {
        LOGGER.info(String.format("get player round results individual get request (result controller) => %s", roundId));
        Optional<PlayerRoundResult> playerResult = resultService.getPlayerRoundResult(roundId, playerId);

        ShowResults showResults = playerResult.get().getResult().getType();

        // get the results
        List<String> playerRoundResults = playerResult.get().getResult().getResult();

        // get the chosen cards
        List<Card> chosenCards = playerResult.get().getResult().getChosenCards();
        // convert to ResultCardDto
        List<ResultCardDto> chosenCardsDto = new ArrayList<>();
        for (Card card : chosenCards) {
            for (Translation translation : card.getTranslations()) {
                chosenCardsDto.add(
                        new ResultCardDto(
                                card.getTags().stream().findFirst().get().getTagValue(),
                                translation.getText(),
                                translation.getLanguage()
                        )
                );
            }
        }

        // get the advice cards
        List<Card> adviceCards = playerResult.get().getResult().getAdviceCards();
        List<ResultCardDto> adviceCardsDto = new ArrayList<>();
        for (Card card : adviceCards) {
            for (Translation translation : card.getTranslations()) {
                adviceCardsDto.add(
                        new ResultCardDto(
                                card.getTags().stream().findFirst().get().getTagValue(),
                                translation.getText(),
                                translation.getLanguage()
                        )
                );
            }
        }

        //get player from game
        List<Player> players = new ArrayList<>(
                gameService.getGame(gameId).get().getLobby().getPlayers()
        );

        PlayerDto playerDto = new PlayerDto(
                players
                        .stream()
                        .filter(player ->
                                player.getId().equals(playerResult.get().getPlayerId())
                        )
                        .findFirst()
                        .get()
                        .getName()
        );

        ResultDto resultDto = new ResultDto(
                playerDto,
                showResults,
                playerRoundResults,
                chosenCardsDto,
                adviceCardsDto
        );

        return ResponseEntity.ok(resultDto);
    }

}
