package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.UUID;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {

    @Id
    @JsonProperty
    private UUID id;

    @ElementCollection(fetch = FetchType.EAGER)
    @JsonProperty
    private Collection<Tag> tags;

    @JsonProperty
    private boolean isActive;
}
