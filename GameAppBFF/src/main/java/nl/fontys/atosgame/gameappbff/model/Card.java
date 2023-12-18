package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.UUID;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "card")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID id;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @JsonProperty
    private Collection<Tag> tags;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @JsonProperty
    private Collection<Translation> translations;

    @JsonProperty
    @LazyCollection(LazyCollectionOption.FALSE)
    private boolean isActive;
}
