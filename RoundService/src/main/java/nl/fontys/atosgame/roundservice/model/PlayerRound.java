package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private UUID playerId;

    @JsonProperty
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Card> likedCards = new ArrayList<>();

    @JsonProperty
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Card> pickedCards = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.PERSIST })
    private List<Card> distributedCards = new ArrayList<>();

    private int nrOfLikedCards;
    private int nrOfPickedCards;
    private String importantTag;

    /**
     * Check if a playerRound is done.
     * A playerRound is done when the player has liked and picked enough cards and has a determinate result.
     * @return True if the playerRound is done, false otherwise.
     */
    public boolean isDone() {
        return (
            likedCards.size() == nrOfLikedCards &&
            pickedCards.size() == nrOfPickedCards &&
            this.hasDeterminateResult()
        );
    }

    /**
     * Check if the playerRound has a determinate result.
     * A playerRound has a determinate result when, in pickedCards, the importantTag is has a value
     * that has been picked most often.
     * @return True if the playerRound has a determinate result, false otherwise.
     */
    public boolean hasDeterminateResult() {
        // Count how often each tag is picked
        Map<String, Integer> tagCount = new HashMap<>();
        for (Card card : pickedCards) {
            for (Tag tag : card.getTags()) {
                if (tag.getTagKey().equals(importantTag)) {
                    tagCount.put(
                        tag.getTagValue(),
                        tagCount.getOrDefault(tag.getTagValue(), 0) + 1
                    );
                }
            }
        }
        // Check if there is a single tag that is picked more often than the others
        if (tagCount.isEmpty()) {
            return false;
        }
        if (tagCount.size() == 1) {
            return true;
        }
        // Get two tags with the highest count
        String highestTagValue = null;
        for (String tagValue : tagCount.keySet()) {
            if (
                highestTagValue == null ||
                tagCount.get(tagValue) > tagCount.get(highestTagValue)
            ) {
                highestTagValue = tagValue;
            }
        }
        String secondHighestTagValue = null;
        for (String tagValue : tagCount.keySet()) {
            if (
                (
                    secondHighestTagValue == null ||
                    tagCount.get(tagValue) > tagCount.get(secondHighestTagValue)
                ) &&
                !tagValue.equals(highestTagValue)
            ) {
                secondHighestTagValue = tagValue;
            }
        }
        // Check if the highest tag is picked more often than the second highest tag
        return tagCount.get(highestTagValue) > tagCount.get(secondHighestTagValue);
    }
}
