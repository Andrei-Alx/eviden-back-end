package nl.fontys.atosgame.roundservice.converters;

/**
 * Converts a cardService.card to a roundService.card
 * @author Eli
 */
public interface CardConverter {
    /**
     * Convert a cardService.card to a roundService.card
     * @param card The card to convert
     * @return The converted card
     */
    nl.fontys.atosgame.roundservice.model.Card convert(nl.fontys.atosgame.cardservice.model.Card card);
}
