package nl.fontys.atosgame.cardservice.model;

import java.util.Locale;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.intellij.lang.annotations.Language;

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
