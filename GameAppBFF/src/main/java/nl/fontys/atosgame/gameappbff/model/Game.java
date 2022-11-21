package nl.fontys.atosgame.gameappbff.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.enums.GameStatus;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

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
}
