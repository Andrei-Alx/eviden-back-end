package nl.fontys.atosgame.gameappbff.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.enums.GameStatus;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Game {

    private UUID id;
    private GameStatus status;
}
