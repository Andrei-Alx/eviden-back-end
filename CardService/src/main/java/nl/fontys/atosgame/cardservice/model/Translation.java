package nl.fontys.atosgame.cardservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.intellij.lang.annotations.Language;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Locale;

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
