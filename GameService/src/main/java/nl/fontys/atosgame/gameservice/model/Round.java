package nl.fontys.atosgame.gameservice.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Round {

    private UUID id;
    private String status;
}
