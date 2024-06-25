package nl.fontys.atosgame.gameservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.enums.GameStatus;
import nl.fontys.atosgame.gameservice.enums.RoundStatus;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String title;

    @OneToOne(fetch = FetchType.EAGER)
    private Lobby lobby;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Round> rounds;

    private String companyType;

    private GameStatus status = GameStatus.CREATED;

    public boolean isDone() {
        return rounds.stream().allMatch((round -> round.getStatus() == (RoundStatus.FINISHED)));
    }
}
