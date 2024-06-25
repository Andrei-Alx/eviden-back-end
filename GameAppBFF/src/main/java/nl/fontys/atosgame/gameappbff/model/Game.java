package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.enums.GameStatus;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Game {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @JsonProperty
    private String title;

    @OneToOne(fetch = FetchType.EAGER)
    private Lobby lobby;

    private String companyType;

    private GameStatus status;

    @OneToMany(fetch = FetchType.EAGER)
    public List<Round> rounds;
}
