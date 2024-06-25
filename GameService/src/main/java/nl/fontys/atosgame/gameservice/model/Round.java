package nl.fontys.atosgame.gameservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.enums.RoundStatus;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Round {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private RoundStatus status;

    @JsonProperty
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    public RoundSettings roundSettings;
}
