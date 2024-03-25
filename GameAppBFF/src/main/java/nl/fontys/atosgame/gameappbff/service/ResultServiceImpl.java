package nl.fontys.atosgame.gameappbff.service;

import java.util.*;
import java.util.stream.Collectors;

import nl.fontys.atosgame.gameappbff.enums.ShowResults;
import nl.fontys.atosgame.gameappbff.model.FinalResult;
import nl.fontys.atosgame.gameappbff.model.PlayerRoundResult;
import nl.fontys.atosgame.gameappbff.model.Result;
import nl.fontys.atosgame.gameappbff.repository.FinalResultRepository;
import nl.fontys.atosgame.gameappbff.repository.PlayerRoundResultRepository;
import nl.fontys.atosgame.gameappbff.repository.ResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service responsible for saving and returning results
 *
 * @author Niek and Kevin
 */

@Service
public class ResultServiceImpl implements ResultService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultServiceImpl.class);
    private final PlayerRoundResultRepository playerRoundResultRepository;
    private final FinalResultRepository finalResultRepository;

    private final ResultRepository resultRepository;

    /**
     * Create a new ResultServiceImpl
     *
     * @param playerRoundResultRepository The repository for playerRoundResults
     * @param finalResultRepository       The repository for finalResults
     * @param resultRepository            The repository for results
     */
    public ResultServiceImpl(
        @Autowired PlayerRoundResultRepository playerRoundResultRepository,
        @Autowired FinalResultRepository finalResultRepository,
        @Autowired ResultRepository resultRepository
    ) {
        this.playerRoundResultRepository = playerRoundResultRepository;
        this.finalResultRepository = finalResultRepository;
        this.resultRepository = resultRepository;
    }

    /**
     * Get a playerRoundResult
     *
     * @param roundId The id of the round
     * @param playerId The id of the player
     * @return PlayerRoundResult The playerRoundResult to return
     */
    @Override
    public Optional<PlayerRoundResult> getPlayerRoundResult(UUID roundId, UUID playerId) {
        Optional<PlayerRoundResult> playerRoundResult = playerRoundResultRepository.findByRoundIdAndPlayerId(
            roundId,
            playerId
        );
        return playerRoundResult;
    }

    /**
     * Get the final results of a game
     * @param gameId The id of the game
     * @return List<PlayerRoundResult> The list of playerRoundResults to return
     */
    @Override
    public Optional<List<PlayerRoundResult>> getFinalResults(UUID gameId) {
        Optional<List<PlayerRoundResult>> finalResults = playerRoundResultRepository.findAllByGameId(gameId);

        // final results round two
        // get the Results of the playerRoundResults where showResults is group
        List<Result> resultsRoundTwo = finalResults.get().stream()
            .filter(playerRoundResult -> playerRoundResult.getResult().getType() == ShowResults.GROUP)
            .map(PlayerRoundResult::getResult)
            .collect(Collectors.toList());

        Map<String, Double> finalResultsRoundTwo = new HashMap<>();
        for (Result result : resultsRoundTwo) {
            int size = result.getResult().size();
            for (int i = 0; i < size; i++) {
                String key = result.getResult().get(i);
                if (finalResultsRoundTwo.containsKey(key)) {
                    finalResultsRoundTwo.put(key, finalResultsRoundTwo.get(key) + 1.0/size);
                } else {
                    finalResultsRoundTwo.put(key, 1.0/size);
                }
            }
        }
        // get top resultCardTypes
        List<String> finalResultsRoundTwoList = getTopResultCardTypes(finalResultsRoundTwo);



        return finalResults;
    }

    public List<String> getTopResultCardTypes(Map<String, Double> tagCount) {
        // get tag with the highest count
        String highestTagValue = null;
        for (String tagValue : tagCount.keySet()) {
            if (
                    highestTagValue == null || tagCount.get(tagValue) > tagCount.get(highestTagValue)
            ) {
                highestTagValue = tagValue;
            }
        }
        // get key from tag(s) with the highest count
        List<String> tagKeys = new ArrayList<>();
        for (String tagValue : tagCount.keySet()){
            if (tagCount.get(tagValue).equals(tagCount.get(highestTagValue))){
                tagKeys.add(tagValue);
            }
        }

        return tagKeys;
    }

    /**
     * Get all playerRoundResults
     *
     * @param roundId The id of the round
     * @return all playerRoundResults of a round
     */
    public Optional<List<PlayerRoundResult>> getPlayerRoundResults(UUID roundId) {
        return playerRoundResultRepository.findAllByRoundId(roundId);
    }

    /**
     * Save a playerRoundResult
     *
     * @param playerRoundResult The playerRoundResult to save
     * @return The saved playerRoundResult
     */
    @Override
    public void handlePlayerResultDetermined(PlayerRoundResult playerRoundResult) {
        System.out.println(playerRoundResult.toString());
        LOGGER.info(String.format("saving results from round to database event => %s", playerRoundResult));
        playerRoundResultRepository.save(playerRoundResult);
    }

    /**
     * Save a finalResult
     *
     * @param finalResult The finalResult to save
     * @return The saved finalResult
     */
    @Override
    public void handleFinalResultDetermined(FinalResult finalResult) {
        finalResultRepository.save(finalResult);
    }
}
