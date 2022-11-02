package nl.fontys.atosgame.roundservice.converters;

/**
 * Converts a cardService.cardSet to a roundService.cardSet
 * @author Eli
 */
public interface CardSetConverter {
    /**
     * Convert a cardService.cardSet to a roundService.cardSet
     * @param cardSet The cardSet to convert
     * @return The converted cardSet
     */
    nl.fontys.atosgame.roundservice.model.CardSet convert(nl.fontys.atosgame.cardservice.model.CardSet cardSet);
}
