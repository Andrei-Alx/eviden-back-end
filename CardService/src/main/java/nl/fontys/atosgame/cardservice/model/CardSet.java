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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardSet {

    @Id
    @GeneratedValue(generator = "UUID")
    @JsonProperty
    private UUID id;

    @JsonProperty
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable()
    @JsonProperty
    private List<Card> cards;

    @JsonProperty
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Tag> tags;

    @JsonProperty
    private boolean isActive;
}
