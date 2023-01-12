package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.dto.ResultDto;
import nl.fontys.atosgame.roundservice.enums.CardSetType;
import nl.fontys.atosgame.roundservice.enums.PlayerRoundPhase;
import nl.fontys.atosgame.roundservice.enums.ResultStatus;
import nl.fontys.atosgame.roundservice.enums.ShowResults;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRound {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @JsonProperty
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID playerId;

    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonProperty
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Card> likedCards = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonProperty
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Card> dislikedCards = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonProperty
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Card> selectedCards = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = { CascadeType.PERSIST })
    private List<Card> distributedCards = new ArrayList<>();

    @Embedded
    private RoundSettings roundSettings;

    /**
     * Check if a playerRound is done.
     * A playerRound is done when the player has liked and picked enough cards and has a determinate result.
     * @return True if the playerRound is done, false otherwise.
     */
    public boolean isDone() {
        return (
            likedCards.size() == roundSettings.getNrOfLikedCards() &&
            selectedCards.size() == roundSettings.getNrOfSelectedCards()
                //&& this.hasDeterminateResult()
        );
    }

    /**
     * Get the current phase of the playerRound.
     * @return The current phase of the playerRound.
     */
    public PlayerRoundPhase getPhase() {
        if (likedCards.size() < roundSettings.getNrOfLikedCards()) {
            return PlayerRoundPhase.LIKING;
        } else if (selectedCards.size() < roundSettings.getNrOfSelectedCards()) {
            return PlayerRoundPhase.PICKING;
        } else {
            return PlayerRoundPhase.RESULT;
        }
    }

    /**
     * Get the results per color of the playerRound.
     * @return The results per color of the playerRound.
     */
    public Map<String, Integer> determineCardsChosenPerType() {
        // Count how often each tag is picked
        Map<String, Integer> cardsChosenPerTag = new HashMap<>();
        for (Card card : selectedCards) {
            for (Tag tag : card.getTags()) {
                if (cardsChosenPerTag.containsKey(tag.getTagValue())) {
                    cardsChosenPerTag.put(tag.getTagValue(), cardsChosenPerTag.get(tag.getTagValue()) + 1);
                } else {
                    cardsChosenPerTag.put(tag.getTagValue(), 1);
                }
            }
        }

        return cardsChosenPerTag;
    }

    /**
     * Check if the playerRound has a determinate result.
     * A playerRound has a determinate result when, in pickedCards, the importantTag has a value
     * that has been picked most often.
     * @return True if the playerRound has a determinate result, false otherwise.
     */
//    public boolean hasDeterminateResult() {
//        Map<String, Integer> tagCount = determineCardsChosenPerType();
//
//        // Check if there is a single tag that is picked more often than the others
//        if (tagCount.isEmpty()) {
//            return true;
//        }
//        if (tagCount.size() == 1) {
//            return true;
//        }
//        // Get two tags with the highest count
//        String highestTagValue = null;
//        for (String tagValue : tagCount.keySet()) {
//            if (
//                highestTagValue == null ||
//                tagCount.get(tagValue) > tagCount.get(highestTagValue)
//            ) {
//                highestTagValue = tagValue;
//            }
//        }
//        String secondHighestTagValue = null;
//        for (String tagValue : tagCount.keySet()) {
//            if (
//                (
//                    secondHighestTagValue == null ||
//                    tagCount.get(tagValue) > tagCount.get(secondHighestTagValue)
//                ) &&
//                !tagValue.equals(highestTagValue)
//            ) {
//                secondHighestTagValue = tagValue;
//            }
//        }
//        // Check if the highest tag is picked more often than the second highest tag
//        return tagCount.get(highestTagValue) > tagCount.get(secondHighestTagValue);
//    }

    public List<String> getTopResultCardTypes(Map<String, Integer> tagCount) {
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
     * Add a card to the likedCards list.
     * Checks if the card is in the hand of the player and if the card is not already liked.
     * @param card
     */
    public void addLikedCard(Card card) {
        if (hasCardInHand(card) && !likedCards.contains(card)) {
            likedCards.add(card);
        } else {
            throw new IllegalArgumentException("Card is not in hand or already liked");
        }
    }

    /**
     * Add a card to the disliked list.
     * Checks if the card is in the hand of the player and if the card is not already disliked.
     * @param card The card to add.
     */
    public void addDislikedCard(Card card) {
        if (hasCardInHand(card) && !dislikedCards.contains(card)) {
            dislikedCards.add(card);
        } else {
            throw new IllegalArgumentException("Card is not in hand or already disliked");
        }
    }

    /**
     * Add a card to the selectedCards list.
     * Checks if the cards are in the hand and if the player has not already picked the cards
     * @param card The card to add.
     */
    public void addSelectedCards(List<Card> card) {
        for (Card c : card) {
            if (hasCardInHand(c) && !selectedCards.contains(c)) {
                selectedCards.add(c);
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
    private boolean hasCardInHand(Card card) {
        return distributedCards.stream().anyMatch(c -> c.getId().equals(card.getId()));
    }
}
