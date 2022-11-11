package nl.fontys.atosgame.gameservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    private UUID id;
    private Lobby lobby;
    private List<Round> rounds;
    private String companyType;
}
