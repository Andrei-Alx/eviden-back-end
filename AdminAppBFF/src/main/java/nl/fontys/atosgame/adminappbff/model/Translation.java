package nl.fontys.atosgame.adminappbff.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Translation {

    @NotNull
    private String language;

    @NotNull
    private String text;
}
