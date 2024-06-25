package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CardSet {

    @Id
    @GeneratedValue(generator = "UUID")
    @JsonProperty
    private UUID id;

    @JsonProperty
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonProperty
    private List<Card> cards;

    @JsonProperty
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tag> tags;

    @JsonProperty
    private boolean isActive;
}
