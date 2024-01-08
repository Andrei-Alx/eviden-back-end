package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.enums.GameStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Game {

    @Id
    private UUID id;

    @JsonProperty
    private String title;

    @OneToOne(fetch = FetchType.EAGER)
    private Lobby lobby;

    private String companyType;

    private GameStatus status;

    @OneToMany(fetch = FetchType.EAGER)
    public List<Round> rounds = new ArrayList<>();

    public void addRound(Round round) {
        rounds.add(round);
    }
}
