package nl.fontys.atosgame.lobbyservice.model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Player {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;
}
