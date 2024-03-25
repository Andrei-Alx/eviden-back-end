package nl.fontys.atosgame.roundservice.service;

import java.util.*;

import nl.fontys.atosgame.roundservice.applicationevents.PlayerRoundFinishedAppEvent;
import nl.fontys.atosgame.roundservice.dto.*;
import nl.fontys.atosgame.roundservice.enums.PlayerRoundPhase;
import nl.fontys.atosgame.roundservice.model.Card;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import nl.fontys.atosgame.roundservice.model.Tag;
import nl.fontys.atosgame.roundservice.repository.PlayerRoundRepository;
import nl.fontys.atosgame.roundservice.service.interfaces.CardService;
import nl.fontys.atosgame.roundservice.service.interfaces.PlayerRoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Service for player round related operations
 * @author Eli
 */
@Service
public class PlayerRoundServiceImpl implements PlayerRoundService {

    private final CardService cardService;
    private final StreamBridge streamBridge;
    private final ApplicationEventPublisher eventPublisher;
    private final PlayerRoundRepository playerRoundRepository;

    public PlayerRoundServiceImpl(
        @Autowired CardService cardService,
        @Autowired StreamBridge streamBridge,
        @Autowired ApplicationEventPublisher eventPublisher,
        @Autowired PlayerRoundRepository playerRoundRepository
    ) {
        this.cardService = cardService;
        this.streamBridge = streamBridge;
        this.eventPublisher = eventPublisher;
        this.playerRoundRepository = playerRoundRepository;
    }

    /**
     * Like a card
     * Produces an event and updates the player round
     *
     * @param playerRound The playerRound to like the card for
     * @param cardId      The card to like
     * @param gameId      The game id
     * @param roundId      The round id
     * @return The updated player round
     */
    @Override
    public PlayerRound likeCard(
        PlayerRound playerRound,
        UUID cardId,
        UUID gameId,
        UUID roundId
    ) {
        Card card = this.cardService.getCard(cardId).get();
        PlayerRoundPhase previousPhase = playerRoundGetPhase(playerRound);
        addLikedCard(playerRound, card);
        PlayerRoundPhase currentPhase = playerRoundGetPhase(playerRound);
        playerRound = playerRoundRepository.save(playerRound);

        // Send event
        streamBridge.send(
            "producePlayerLikedCard-in-0",
            new CardLikedEventDto(playerRound.getPlayerId(), gameId, roundId, cardId)
        );

        // check if phase has ended
        if (previousPhase != currentPhase) {
            phaseEnded(playerRound, gameId, roundId, previousPhase, currentPhase);
        }

        return playerRound;
    }

    /**
     * Dislike a card
     * Produces an event and updates the player round
     *
     * @param playerRound The playerRound to dislike the card for
     * @param cardId      The card to dislike
     * @param gameId      The game id
     * @param roundId      The round id
     * @return
     */
    @Override
    public PlayerRound dislikeCard(
        PlayerRound playerRound,
        UUID cardId,
        UUID gameId,
        UUID roundId
    ) {
        Card card = this.cardService.getCard(cardId).get();
        addDislikedCard(playerRound, card);
        playerRound = playerRoundRepository.save(playerRound);

        streamBridge.send(
            "producePlayerDislikedCard-in-0",
            new CardDislikedEventDto(playerRound.getPlayerId(), gameId, roundId, cardId)
        );
        return playerRound;
    }

    /**
     * Add cards to the selected cards of the player round
     * Produces an event and updates the round
     *
     * @param playerRound The playerRound to selected the cards for
     * @param cardIds     The ids of the cards to select
     * @param gameId      The game id
     * @param roundId      The round id
     * @return The updated player round
     */
    @Override
    public PlayerRound selectCards(
        PlayerRound playerRound,
        List<UUID> cardIds,
        UUID gameId,
        UUID roundId
    ) {
        Collection<Card> cards = this.cardService.getCards(cardIds);
        PlayerRoundPhase previousPhase = playerRoundGetPhase(playerRound);
        addSelectedCards(playerRound, List.copyOf(cards));
        PlayerRoundPhase currentPhase = playerRoundGetPhase(playerRound);
        playerRound = playerRoundRepository.save(playerRound);

        // Send event
        streamBridge.send(
            "producePlayerSelectedCards-in-0",
            new CardsSelectedEventDto(playerRound.getPlayerId(), cardIds, roundId, gameId)
        );

        // Check if playerRound is finished
        if (playerRoundIsDone(playerRound)) {
            this.endPlayerRound(playerRound);
        }

        if (previousPhase != currentPhase) {
            phaseEnded(playerRound, gameId, roundId, previousPhase, currentPhase);
        }

        return playerRound;
    }

    /**
     * Check if a player round is finished and sends an application event if it is
     * @param playerRound The player round to check
     */
    public void endPlayerRound(PlayerRound playerRound) {
        eventPublisher.publishEvent(new PlayerRoundFinishedAppEvent(this, playerRound));
    }

    /**
     * Check if a playerRound is done.
     * A playerRound is done when the player has liked and picked enough cards and has a determinate result.
     * @return True if the playerRound is done, false otherwise.
     */
    @Override
    public boolean playerRoundIsDone(PlayerRound playerRound) {
        return (
                playerRound.getLikedCards().size() == playerRound.getRoundSettings().getNrOfLikedCards() &&
                        playerRound.getSelectedCards().size() == playerRound.getRoundSettings().getNrOfSelectedCards()
        );
    }

    /**
     * Get the current phase of the playerRound.
     * @return The current phase of the playerRound.
     * @param playerRound The player round to check
     */
    @Override
    public PlayerRoundPhase playerRoundGetPhase(PlayerRound playerRound) {
        if (playerRound.getLikedCards().size() < playerRound.getRoundSettings().getNrOfLikedCards()) {
            return PlayerRoundPhase.LIKING;
        } else if (playerRound.getSelectedCards().size() < playerRound.getRoundSettings().getNrOfSelectedCards()) {
            return PlayerRoundPhase.PICKING;
        } else {
            return PlayerRoundPhase.RESULT;
        }
    }

    /**
     * Get the results per color of the playerRound.
     * @param playerRound The player round to check
     * @return The results per color of the playerRound.
     */
    @Override
    public Map<String, Integer> determineCardsChosenPerType(PlayerRound playerRound) {
        // Count how often each tag is picked
        Map<String, Integer> cardsChosenPerTag = new HashMap<>();
        for (Card card : playerRound.getSelectedCards()) {
            for (Tag tag : card.getTags()) {
                if (cardsChosenPerTag.containsKey(tag.getTagValue())) {
                    cardsChosenPerTag.put(
                            tag.getTagValue(),
                            cardsChosenPerTag.get(tag.getTagValue()) + 1
                    );
                } else {
                    cardsChosenPerTag.put(tag.getTagValue(), 1);
                }
            }
        }

        return cardsChosenPerTag;
    }

    @Override
    public List<String> getTopResultCardTypes(PlayerRound playerRound, Map<String, Integer> tagCount) {
        // get tag with the highest count
        String highestTagValue = null;
        for (String tagValue : tagCount.keySet()) {
            if (
                    highestTagValue == null ||
                            tagCount.get(tagValue) > tagCount.get(highestTagValue)
            ) {
                highestTagValue = tagValue;
            }
        }
        // get key from tag(s) with the highest count
        List<String> tagKeys = new ArrayList<>();
        for (String tagValue : tagCount.keySet()) {
            if (tagCount.get(tagValue).equals(tagCount.get(highestTagValue))) {
                tagKeys.add(tagValue);
            }
        }

        return tagKeys;
    }


    /**
     * Add a card to the likedCards list.
     * Checks if the card is in the hand of the player and if the card is not already liked.
     * @param playerRound The player round to check
     * @param card
     */
    @Override
    public void addLikedCard(PlayerRound playerRound, Card card) {
        if (hasCardInHand(playerRound, card) && !playerRound.getLikedCards().contains(card)) {
            playerRound.getLikedCards().add(card);
        } else {
            throw new IllegalArgumentException("Card is not in hand or already liked");
        }
    }

    /**
     * Add a card to the disliked list.
     * Checks if the card is in the hand of the player and if the card is not already disliked.
     * @param playerRound The player round to check
     * @param card The card to add.
     */
    @Override
    public void addDislikedCard(PlayerRound playerRound, Card card) {
        List<Card> dislikedCards = playerRound.getDislikedCards();

        if (hasCardInHand(playerRound, card) && !dislikedCards.contains(card)) {
            dislikedCards.add(card);
            playerRound.setDislikedCards(dislikedCards);

        } else {
            throw new IllegalArgumentException("Card is not in hand or already disliked");
        }
    }

    /**
     * Add a card to the selectedCards list.
     * Checks if the cards are in the hand and if the player has not already picked the cards
     * @param playerRound The player round to check
     * @param card The card to add.
     */
    @Override
    public void addSelectedCards(PlayerRound playerRound, List<Card> card) {
        List<Card> selectedCards = playerRound.getSelectedCards();

        for (Card c : card) {
            if (hasCardInHand(playerRound, c) && !selectedCards.contains(c)) {
                selectedCards.add(c);
                playerRound.setSelectedCards(selectedCards);

            } else {
                throw new IllegalArgumentException(
                        "Card is not in hand or already selected"
                );
            }
        }
    }

    /**
     * Check if a card is in the hand of the player.
     * @param card The card to check.
     * @return True if the card is in the hand of the player, false otherwise.
     */
    private boolean hasCardInHand(PlayerRound playerRound, Card card) {
        return playerRound.getDistributedCards().stream().anyMatch(c -> c.getId().equals(card.getId()));
    }

    private void phaseEnded(PlayerRound playerRound, UUID gameId, UUID roundId, PlayerRoundPhase previousPhase, PlayerRoundPhase currentPhase) {
        streamBridge.send(
                "producePlayerPhaseEnded-in-0",
                new PlayerPhaseEndedDto(
                        previousPhase.ordinal(),
                        playerRound.getPlayerId(),
                        gameId,
                        roundId
                )
        );
        streamBridge.send(
                "producePlayerPhaseStarted-in-0",
                new PlayerPhaseStartedDto(
                        currentPhase.ordinal(),
                        playerRound.getPlayerId(),
                        gameId,
                        roundId
                )
        );
    }
}
