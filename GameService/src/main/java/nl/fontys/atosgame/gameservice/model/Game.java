package nl.fontys.atosgame.gameservice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.enums.GameStatus;
import nl.fontys.atosgame.gameservice.enums.RoundStatus;

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
    private List<Round> rounds = new ArrayList<>();

    private String companyType;

    private GameStatus status = GameStatus.CREATED;
}
