package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {

    @Id
    @GeneratedValue(generator = "uuid")
    @JsonProperty
    private UUID id;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonProperty
    private List<Tag> tags;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonProperty
    private List<Translation> translations;

    @JsonProperty
    private boolean isActive;
}
