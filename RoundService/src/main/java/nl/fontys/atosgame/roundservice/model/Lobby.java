package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lobby {
    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID id;
    @ElementCollection
    @JsonProperty
    private List<UUID> playerIds = new java.util.ArrayList<>();
}
