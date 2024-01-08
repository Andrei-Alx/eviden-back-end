package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.enums.RoundStatus;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Round {

    @Id
    @JsonProperty
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonProperty
    private List<PlayerRound> playerRounds = new ArrayList<>();

    @JsonProperty
    private RoundStatus status;

    @JsonProperty
    @Embedded
    public RoundSettings roundSettings;

    public void addPlayerRound(PlayerRound playerRound) {
        playerRounds.add(playerRound);
    }
}
