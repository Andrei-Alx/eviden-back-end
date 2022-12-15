package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinalResult {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID id;

    @JsonProperty
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID playerId;

    @JsonProperty
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID gameId;

    @JsonProperty
    @OneToMany(cascade = { CascadeType.PERSIST })
    private Collection<Result> results = new java.util.ArrayList<>();
}
