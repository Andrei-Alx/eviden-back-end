package nl.fontys.atosgame.roundservice.service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import nl.fontys.atosgame.roundservice.model.Card;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import nl.fontys.atosgame.roundservice.model.Round;
import nl.fontys.atosgame.roundservice.repository.PlayerRoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class PlayerRoundServiceImpl implements PlayerRoundService {

    private CardService cardService;
    private PlayerRoundRepository playerRoundRepository;

    public PlayerRoundServiceImpl(
            @Autowired CardService cardService,
            @Autowired PlayerRoundRepository playerRoundRepository
    ) {
        this.cardService = cardService;
        this.playerRoundRepository = playerRoundRepository;
    }

    /**
     * Like a card
     * Produces an event and updates the player round
     *
     * @param playerRound The playerRound to like the card for
     * @param cardId      The card to like
     * @return The updated player round
     */
    @Override
    public PlayerRound likeCard(PlayerRound playerRound, UUID cardId) {
        Card card = this.cardService.getCard(cardId).get();
        playerRound.addLikedCard(card);
        playerRound = playerRoundRepository.save(playerRound);

        // TODO: produce event
        return playerRound;
    }

    /**
     * Dislike a card
     * Produces an event and updates the player round
     *
     * @param playerRound The playerRound to dislike the card for
     * @param cardId      The card to dislike
     * @return
     */
    @Override
    public PlayerRound dislikeCard(PlayerRound playerRound, UUID cardId) {
        Card card = this.cardService.getCard(cardId).get();
        playerRound.addDislikedCard(card);
        playerRound = playerRoundRepository.save(playerRound);

        // TODO: produce event
        return playerRound;
    }

    /**
     * Add cards to the selected cards of the player round
     * Produces an event and updates the round
     *
     * @param playerRound The playerRound to selected the cards for
     * @param cardIds     The ids of the cards to select
     * @return The updated player round
     */
    @Override
    public PlayerRound selectCards(PlayerRound playerRound, List<UUID> cardIds) {
        Collection<Card> cards = this.cardService.getCards(cardIds);
        playerRound.addSelectedCards(List.copyOf(cards));
        playerRound = playerRoundRepository.save(playerRound);

        // TODO: produce event
        return playerRound;
    }
}
