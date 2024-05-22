package nl.fontys.atosgame.cardservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {
    @Id
    @GeneratedValue(generator = "UUID")
    @JsonProperty
    private UUID id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonProperty
    private List<Tag> tags;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonProperty
    private List<Translation> translations;

    @JsonProperty
    private boolean isActive;
}
