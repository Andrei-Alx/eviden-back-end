package nl.fontys.atosgame.lobbyservice.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LobbySettings {

    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "lobbysettings_id")
    private UUID id;

    private int maxPlayers;
}
