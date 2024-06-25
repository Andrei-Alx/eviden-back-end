package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.enums.RoundStatus;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Round {

    @Id
    @GeneratedValue(generator = "UUID")
    @JsonProperty
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonProperty
    private List<PlayerRound> playerRounds;

    @JsonProperty
    private RoundStatus status;

    @JsonProperty
    @OneToOne
    private RoundSettings roundSettings;

    /**
     * Add a playerRound to the round
     * @param playerRound The playerRound to add
     */
    public void addPlayerRound(PlayerRound playerRound) {
        playerRounds.add(playerRound);
    }

    /**
     * Check if the round is done.
     * A round is done when all playerRounds are done.
     * @return True if the round is done, false otherwise.
     */
    public boolean isDone() {
        return playerRounds.stream().allMatch(PlayerRound::isDone);
    }

    /**
     * Get the playerRound for a player
     * @param playerId The id of the player
     * @return The playerRound for the player
     */
    public PlayerRound getPlayerRound(UUID playerId) {
        return playerRounds
            .stream()
            .filter(playerRound -> playerRound.getPlayerId().equals(playerId))
            .findFirst()
            .orElse(null);
    }
}
