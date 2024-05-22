package nl.fontys.atosgame.cardservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.cardservice.enums.TagType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tag {
    @Id
    @GeneratedValue(generator = "UUID")
    @JsonProperty
    private UUID id;

    @JsonProperty
    @Enumerated(EnumType.STRING)
    private TagType tagKey;

    @JsonProperty
    private String tagValue;
}
