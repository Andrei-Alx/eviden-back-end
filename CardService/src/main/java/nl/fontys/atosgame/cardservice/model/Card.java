package nl.fontys.atosgame.cardservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {

    @Id
    @GeneratedValue(generator = "UUID")
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
