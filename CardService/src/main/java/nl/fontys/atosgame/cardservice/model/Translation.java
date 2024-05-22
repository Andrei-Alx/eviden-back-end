package nl.fontys.atosgame.cardservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Translation {
    @Id
    @GeneratedValue(generator = "UUID")
    @JsonProperty
    private UUID id;

    @NotNull
    private String language;

    @NotNull
    private String text;
}
