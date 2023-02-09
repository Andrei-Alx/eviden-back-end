package nl.fontys.atosgame.gameservice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.enums.GameStatus;
import nl.fontys.atosgame.gameservice.enums.RoundStatus;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private String title;

    @OneToOne
    private Lobby lobby;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Round> rounds = new ArrayList<>();

    private String companyType;

    private GameStatus status = GameStatus.CREATED;

    public boolean isDone() {
        return rounds.stream().allMatch((round -> round.getStatus() == (RoundStatus.FINISHED)));
    }
}
