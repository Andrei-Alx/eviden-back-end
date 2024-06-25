package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.enums.RoundStatus;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Round {

    @Id
    @GeneratedValue(generator = "UUID")
    @JsonProperty
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonProperty
    private List<PlayerRound> playerRounds;

    @JsonProperty
    private RoundStatus status;

    @JsonProperty
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public RoundSettings roundSettings;

    public void addPlayerRound(PlayerRound playerRound) {
        playerRounds.add(playerRound);
    }
}
