package nl.fontys.atosgame.lobbyservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.UUID;

public class Lobby {
    /*@Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonProperty*/
    private UUID id;

    /*@ElementCollection
    @JsonProperty*/
    private Collection<Player> players;

    private String lobbyCode;

    private LobbySettings lobbySettings;
}
