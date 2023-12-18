package nl.fontys.atosgame.cardservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.cardservice.enums.TagType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Tag {

    @JsonProperty
    @Enumerated(EnumType.STRING)
    private TagType tagKey;

    @JsonProperty
    private String tagValue;
}
