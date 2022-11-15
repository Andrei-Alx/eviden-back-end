package nl.fontys.atosgame.lobbyservice.model;

import java.util.UUID;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Player {

    // TODO
    private UUID id;
    private String name;
}
