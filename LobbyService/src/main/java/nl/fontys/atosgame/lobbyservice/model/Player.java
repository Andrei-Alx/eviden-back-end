package nl.fontys.atosgame.lobbyservice.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    // TODO
    private UUID id;
    private String name;
}
