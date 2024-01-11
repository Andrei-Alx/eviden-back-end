package nl.fontys.atosgame.lobbyservice.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Player {

    // TODO
    @Column(name = "player_id")
    private UUID id;

    private String name;
}
