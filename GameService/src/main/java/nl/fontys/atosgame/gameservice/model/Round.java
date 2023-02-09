package nl.fontys.atosgame.gameservice.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.enums.RoundStatus;
import org.hibernate.annotations.Type;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Round {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private RoundStatus status;
}
