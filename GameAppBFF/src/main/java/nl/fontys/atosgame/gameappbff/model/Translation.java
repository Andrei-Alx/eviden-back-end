package nl.fontys.atosgame.gameappbff.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

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
