package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.UUID;
import jakarta.persistence.*;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinalResult {

    @Id
    @GeneratedValue(generator = "UUID")
    @JsonProperty
    private UUID id;

    @JsonProperty
    private UUID playerId;

    @JsonProperty
    private UUID gameId;

    @JsonProperty
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Collection<Result> results = new java.util.ArrayList<>();
}
