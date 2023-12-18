package nl.fontys.atosgame.cardservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

    @ElementCollection
    @JsonProperty
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Tag> tags;

    @ElementCollection
    @JsonProperty
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Translation> translations;

    @JsonProperty
    @LazyCollection(LazyCollectionOption.FALSE)
    private boolean isActive;
}
