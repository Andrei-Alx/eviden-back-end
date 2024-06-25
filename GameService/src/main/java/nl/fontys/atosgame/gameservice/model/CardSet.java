package nl.fontys.atosgame.gameservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CardSet {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @JsonProperty
    private boolean isActive;
}
