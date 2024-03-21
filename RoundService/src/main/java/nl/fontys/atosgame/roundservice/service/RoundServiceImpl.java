package nl.fontys.atosgame.roundservice.service;

import java.util.*;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;
import nl.fontys.atosgame.roundservice.dto.CardsDistributedDto;
import nl.fontys.atosgame.roundservice.dto.PlayerPhaseStartedDto;
import nl.fontys.atosgame.roundservice.dto.PlayerResultDeterminedDto;
import nl.fontys.atosgame.roundservice.dto.ResultDto;
import nl.fontys.atosgame.roundservice.dto.RoundEndedDto;
import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.dto.RoundStartedDto;
import nl.fontys.atosgame.roundservice.enums.RoundStatus;
import nl.fontys.atosgame.roundservice.enums.ShowResults;
import nl.fontys.atosgame.roundservice.enums.TagType;
import nl.fontys.atosgame.roundservice.event.produced.RoundCreatedEventKeyValue;
import nl.fontys.atosgame.roundservice.model.*;
import nl.fontys.atosgame.roundservice.repository.RoundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

/**
 * Service for round related operations
 *
 * @author Eli
 */
@Service
public class RoundServiceImpl implements RoundService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoundServiceImpl.class);
    private final RoundRepository roundRepository;

    private final CardSetService cardSetService;

    private final PlayerRoundService playerRoundService;

    private final RoundLogicService roundLogicService;

    private final StreamBridge streamBridge;

    public RoundServiceImpl(
        @Autowired RoundRepository roundRepository,
        @Autowired CardSetService cardSetService,
        @Autowired StreamBridge streamBridge,
        @Autowired PlayerRoundService playerRoundService,
        @Autowired RoundLogicService roundLogicService
    ) {
        this.roundRepository = roundRepository;
        this.cardSetService = cardSetService;
        this.playerRoundService = playerRoundService;
        this.streamBridge = streamBridge;
        this.roundLogicService = roundLogicService;
    }

    /**
     * Create rounds for a game
     *
     * @param gameId        The id of the game
     * @param roundSettings The settings for the rounds
     * @return
     */
    @Override
    public List<Round> createRounds(UUID gameId, List<RoundSettingsDto> roundSettings) {
        List<Round> rounds = new ArrayList<>();

        ShowResults targetShowResults = ShowResults.PERSONAL;


        RoundSettingsDto foundDto = null;
        Iterator<RoundSettingsDto> iterator = roundSettings.iterator();
        while (iterator.hasNext()) {
            RoundSettingsDto dto = iterator.next();
            if (dto.getShowPersonalOrGroupResults() == targetShowResults)
            {
                foundDto = dto;
                iterator.remove();
                rounds.add(createRound(gameId, foundDto));
                break;
            }
        }
        for (RoundSettingsDto roundSetting : roundSettings) {
            rounds.add(createRound(gameId, roundSetting));
        }

        return rounds;
    }

    /**
     * Start a round
     * Changes the status of the round to InProgress
     * Distributes the cards to the players
     *
     * @param roundId   The id of the round
     * @param playerIds the ids of the players to start the round for
     * @param gameId    The id of the game
     * @return The updated round
     */
    @Override
    public Round startRound(UUID roundId, List<UUID> playerIds, UUID gameId) {
        // Get the round
        Optional<Round> roundOptional = roundRepository.findById(roundId);
        if (roundOptional.isEmpty()) {
            throw new EntityNotFoundException("Round not found");
        }
        Round round = roundOptional.get();

        // Initialize the round so that it has all the player rounds
        round = roundLogicService.initializeRound(round, playerIds);

        // Change the status of the round to InProgress and send event
        round.setStatus(RoundStatus.IN_PROGRESS);

        // Distribute the cards to the players
        round = roundLogicService.distributeCards(round);

        // Save to db
        round = roundRepository.save(round);

        // Send round started event
        streamBridge.send(
            "produceRoundStarted-in-0",
            new RoundStartedDto(gameId, roundId)
        );

        // TODO: send distributed cards events with P-19 (and add to unit test)
        for (PlayerRound playerRound : round.getPlayerRounds()) {
            streamBridge.send(
                "producePlayerCardsDistributed-in-0",
                new CardsDistributedDto(
                    roundId,
                    playerRound
                        .getDistributedCards()
                        .stream()
                        .map(Card::getId)
                        .collect(Collectors.toList()),
                    playerRound.getPlayerId(),
                    gameId
                )
            );
        }

        for (PlayerRound playerRound : round.getPlayerRounds()) {
            streamBridge.send(
                "producePlayerPhaseStarted-in-0",
                new PlayerPhaseStartedDto(0, playerRound.getPlayerId(), gameId, roundId)
            );
        }

        return round;
    }

    /**
     * End a round
     *
     * @param roundId The id of the round
     * @param gameId  The id of the game
     * @return The updated round
     */
    @Override
    public Round endRound(UUID roundId, UUID gameId) {
        // Get the round
        Round round = roundRepository
            .findById(roundId)
            .orElseThrow(EntityNotFoundException::new);

        // Change the status of the round to Finished and send event
        round.setStatus(RoundStatus.FINISHED);
        streamBridge.send("produceRoundEnded-in-0", new RoundEndedDto(gameId, roundId));

        // Publish results
        //this.publishResults(roundId, gameId);

        // Save to db
        round = roundRepository.save(round);
        return round;
    }

    /**
     * Get a round by id
     *
     * @param roundId The id of the round
     * @return The round
     */
    @Override
    public Optional<Round> getRound(UUID roundId) {
        return roundRepository.findById(roundId);
    }

    /**
     * Like a card
     * Produces an event and updates the player round
     *
     * @param playerId The player to like the card for
     * @param cardId   The card to like
     * @param gameId   The game id
     * @param roundId  The round id
     * @return The updated player round
     */
    @Override
    public Round likeCard(UUID playerId, UUID cardId, UUID gameId, UUID roundId) {
        Round round = getRound(roundId).orElseThrow(EntityNotFoundException::new);
        LOGGER.info(String.format("Round service liking card => %s", round));
        playerRoundService.likeCard(
            getPlayerRound(round, playerId),
            cardId,
            gameId,
            roundId
        );
        return round;
    }

    /**
     * Dislike a card
     * Produces an event and updates the player round
     *
     * @param playerId The player to dislike the card for
     * @param cardId   The card to dislike
     * @param gameId   The game id
     * @param roundId  The round id
     * @return
     */
    @Override
    public Round dislikeCard(UUID playerId, UUID cardId, UUID gameId, UUID roundId) {
        Round round = getRound(roundId).orElseThrow(EntityNotFoundException::new);
        playerRoundService.dislikeCard(
            getPlayerRound(round, playerId),
            cardId,
            gameId,
            roundId
        );
        return round;
    }

    /**
     * Add cards to the selected cards of the player round
     * Produces an event and updates the round
     *
     * @param playerId The id of the player
     * @param cardIds  The ids of the cards to select
     * @param gameId   The id of the game
     * @param roundId  The id of the round
     * @return The updated round
     */
    @Override
    public Round selectCards(
        UUID playerId,
        List<UUID> cardIds,
        UUID gameId,
        UUID roundId
    ) {
        Round round = getRound(roundId).orElseThrow(EntityNotFoundException::new);
        playerRoundService.selectCards(
            getPlayerRound(round, playerId),
            cardIds,
            gameId,
            roundId
        );

        return round;
    }

    /**
     * When a playerround is finished, check if round is finished and if so, launch application event
     *
     * @param roundId The id of the round
     * @param gameId  The id of the game
     */
    @Override
    public boolean checkRoundEnd(UUID roundId, UUID gameId) {
        Round round = getRound(roundId).get();
        if (roundIsDone(round)) {
            this.endRound(roundId, gameId);
            return true;
        }

        return false;
    }

    /**
     * Get the round that contains a playerround
     *
     * @param playerRound The player round
     * @return The round
     */
    @Override
    public Optional<Round> getRoundByPlayerRound(PlayerRound playerRound) {
        return roundRepository.findByPlayerRoundsContaining(playerRound);
    }

    /**
     * Publish the results of a round
     *
     * @param roundId The id of the round
     * @param gameId  The id of the game
     */
    @Override
    public void publishResults(UUID roundId, UUID gameId, UUID playerId) {

        Optional<Round> roundOptional = getRound(roundId);
        if (roundOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        Round round = roundOptional.get();
        round
            .getPlayerRounds()
            .forEach(playerRound -> {
                if(playerRoundService.playerRoundIsDone(playerRound)) {
                    // playerRound calculate results and produce in event

                    // calculate the amount a card type (color or operation model) is picked
                    Map<String, Integer> tempResults = playerRoundService.determineCardsChosenPerType(playerRound);

                    // get back a list of results when *undetermined*
                    // get back a single result when *determined*
                    List<String> result = playerRoundService.getTopResultCardTypes(playerRound, tempResults);

                    // get the importantTag
                    String importantTagValue = playerRound
                            .getRoundSettings()
                            .getCardSet()
                            .getTags()
                            .stream()
                            .filter(tag -> tag.getTagKey() == TagType.IMPORTANT_TAG)
                            .findFirst()
                            .get()
                            .getTagValue();
                    Tag importantTag = new Tag(TagType.IMPORTANT_TAG, importantTagValue);

                    // Type is advice
                    Tag adviceTag = new Tag(TagType.TYPE, "advice");

                    // group or personal
                    String groupOrPersonalTagValue = playerRound
                            .getRoundSettings()
                            .getCardSet()
                            .getTags()
                            .stream()
                            .filter(tag -> tag.getTagKey() == TagType.GROUP_OR_PERSONAL)
                            .findFirst()
                            .get()
                            .getTagValue();
                    Tag groupOrPersonalTag = new Tag(
                            TagType.GROUP_OR_PERSONAL,
                            groupOrPersonalTagValue
                    );

                    List<Tag> tags = new ArrayList<>();
                    tags.add(importantTag);
                    tags.add(adviceTag);
                    tags.add(groupOrPersonalTag);

                    // get all cardSets
                    List<CardSet> cardSet = cardSetService.getAllCardSets();

                    // get the cardSet that matches the tags
                    CardSet cardSetToUse = cardSet
                            .stream()
                            .filter(cardSet1 -> cardSet1.getTags().containsAll(tags))
                            .findFirst()
                            .get();

                    // advice cards that match the result cards
                    List<Card> allAdviceCards = new ArrayList<>(cardSetToUse.getCards());
                    List<Card> adviceCards = allAdviceCards
                            .stream()
                            .filter(card ->
                                    card
                                            .getTags()
                                            .stream()
                                            .anyMatch(tag ->
                                                    result.stream().anyMatch(tag.getTagValue()::equals)
                                            )
                            )
                            .collect(Collectors.toList());

                    // roundSettings showResult
                    ShowResults showResults = playerRound
                            .getRoundSettings()
                            .getShowPersonalOrGroupResults();

                    // playerRound selectedCards
                    List<Card> selectedCards = playerRound.getSelectedCards();

                    ResultDto resultDto = new ResultDto(
                            playerRound.getPlayerId(),
                            showResults,
                            result,
                            selectedCards,
                            adviceCards
                    );

                    PlayerResultDeterminedDto dto = new PlayerResultDeterminedDto();
                    dto.setGameId(gameId);
                    dto.setRoundId(roundId);
                    dto.setPlayerId(playerRound.getPlayerId());
                    dto.setResult(resultDto);

                    LOGGER.info(playerId.toString());
                    LOGGER.info(playerRound.getPlayerId().toString());

                    if(Objects.equals(playerId.toString(), playerRound.getPlayerId().toString()))
                    {
                        // Send event
                        LOGGER.info("PUBLISHING RESULTS IN FILE ROUNDSERVICEIMPL");
                        streamBridge.send("producePlayerResultDetermined-out-0", dto);
                    }

                }

            });
    }

    /**
     * Create a round for a game
     *
     * @param gameId        The id of the game
     * @param roundSettings The settings for the round
     */
    public Round createRound(UUID gameId, RoundSettingsDto roundSettings) {
        Round round = new Round(null, new ArrayList<>(), RoundStatus.CREATED, null);
        // Create round settings
        RoundSettings settings = new RoundSettings(
            roundSettings.getShowPersonalOrGroupResults(),
            roundSettings.getNrOfLikedCards(),
            roundSettings.getNrOfSelectedCards(),
            roundSettings.getShuffleMethod(),
            roundSettings.isShowSameCardOrder(),
            null
        );
        // Get card set
        Optional<CardSet> cardSet = cardSetService.getCardSet(
            roundSettings.getCardSetId()
        );
        if (cardSet.isEmpty()) {
            throw new IllegalArgumentException("Card set not found");
        }

        settings.setCardSet(cardSet.get());

        // Add round settings to round
        round.setRoundSettings(settings);

        // Save round
        round = roundRepository.save(round);

        // Produce round created event
        streamBridge.send(
            "produceRoundCreated-in-0",
            new RoundCreatedEventKeyValue(gameId, round)
        );
        return round;
    }

    /**
     * Add a playerRound to the round
     * @param round The round to add to
     * @param playerRound The playerRound to add
     */
    @Override
    public void addPlayerRound(Round round, PlayerRound playerRound) {
        round.getPlayerRounds().add(playerRound);
    }

    /**
     * Check if the round is done.
     * A round is done when all playerRounds are done.
     * @param round the round to check
     * @return True if the round is done, false otherwise.
     */
    @Override
    public boolean roundIsDone(Round round) {
        for (PlayerRound playerRound : round.getPlayerRounds()) {
            if (!playerRoundService.playerRoundIsDone(playerRound)){
                return false;
            }
        }
        return true;
    }


    /**
     * Get the playerRound for a player
     * @param round the round to get the players from
     * @param playerId The id of the player
     * @return The playerRound for the player
     */
    @Override
    public PlayerRound getPlayerRound(Round round, UUID playerId) {
        return round.getPlayerRounds()
                .stream()
                .filter(playerRound -> playerRound.getPlayerId().equals(playerId))
                .findFirst()
                .orElse(null);
    }
}
