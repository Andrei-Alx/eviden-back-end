package nl.fontys.atosgame.lobbyservice.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Player {

    // TODO
    @Column(name = "player_id")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private String name;
}
