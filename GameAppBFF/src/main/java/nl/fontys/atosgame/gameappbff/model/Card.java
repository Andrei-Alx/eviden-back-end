package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "card")
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

    @ElementCollection(fetch = FetchType.EAGER)
    @JsonProperty
    private Collection<Translation> translations;

    @JsonProperty
    private boolean isActive;
}
