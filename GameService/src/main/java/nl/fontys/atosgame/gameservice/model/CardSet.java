package nl.fontys.atosgame.gameservice.model;

import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CardSet {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
}
