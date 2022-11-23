package nl.fontys.atosgame.gameappbff.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

    @OneToOne
    private Lobby lobby;

    private GameStatus status;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    private List<Round> rounds = new ArrayList<>();

    public void addRound(Round round) {
        rounds.add(round);
    }
}
