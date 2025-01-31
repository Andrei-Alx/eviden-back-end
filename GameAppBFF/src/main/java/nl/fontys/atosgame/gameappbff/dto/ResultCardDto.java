package nl.fontys.atosgame.gameappbff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultCardDto {

    private String tag;
    private String text;
    private String language;
}
