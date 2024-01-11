package nl.fontys.atosgame.gameservice.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.enums.RoundStatus;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Round {

    @Id
    private UUID id;

    private RoundStatus status;

    @JsonProperty
    @Embedded
    public RoundSettings roundSettings;
}
