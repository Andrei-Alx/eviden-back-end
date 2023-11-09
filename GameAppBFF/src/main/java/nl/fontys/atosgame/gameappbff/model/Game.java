package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.enums.GameStatus;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Game {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @JsonProperty
    private String title;

    @OneToOne
    private Lobby lobby;

    private String companyType;

    private GameStatus status;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    public List<Round> rounds = new ArrayList<>();

    public void addRound(Round round) {
        rounds.add(round);
    }
}
