package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CardSet {

    @Id
    @JsonProperty
    private UUID id;

    @JsonProperty
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonProperty
    private List<Card> cards;

    @JsonProperty
    @OneToMany(fetch = FetchType.EAGER)
    private List<Tag> tags;

    @JsonProperty
    private boolean isActive;
}
