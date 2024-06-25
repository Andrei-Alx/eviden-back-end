package nl.fontys.atosgame.lobbyservice.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LobbySettings {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private int maxPlayers;
}
