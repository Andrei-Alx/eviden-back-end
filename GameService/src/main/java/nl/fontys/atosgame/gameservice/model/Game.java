package nl.fontys.atosgame.gameservice.model;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.enums.GameStatus;
import org.hibernate.annotations.Type;

import javax.persistence.*;

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
    private List<Round> rounds;
    private String companyType;

    private GameStatus status = GameStatus.CREATED;
}
