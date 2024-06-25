package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.enums.TagType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Tag {

    @Id
    @GeneratedValue(generator = "UUID")
    @JsonProperty
    private UUID id;

    @JsonProperty
    private TagType tagKey;

    @JsonProperty
    private String tagValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return tagKey == tag.tagKey && Objects.equals(tagValue, tag.tagValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagKey, tagValue);
    }
}
