package nl.fontys.atosgame.adminappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Tag {
    @JsonProperty
    private String tagKey;
    @JsonProperty
    private String tagValue;
}
