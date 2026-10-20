package blackjack.src.Table;

import java.util.ArrayList;
import java.util.List;

import blackjack.src.Player.BlackjackPlayer;
import Engine.Card.StdCard;
import Engine.SetCard.Deck;
import Engine.Table.Table;

/**
 * Mesa de Blackjack: jugadores + banca + mazo de robo + descarte.
 * La banca NO está en la lista de jugadores (no tiene turno propio en el Engine),
 * la maneja BlackjackEngine al final de la ronda.
 */
public class BlackjackTable extends Table<BlackjackPlayer, StdCard> {
    private static final String[] SUITS = {"Spades", "Hearts", "Diamonds", "Clubs"};

    private final BlackjackPlayer dealer;
    private final Deck<StdCard> discardDeck;

    public BlackjackTable(List<StdCard> deck) {
        super(deck);
        dealer = new BlackjackPlayer();
        dealer.setName("Dealer");
        discardDeck = new Deck<StdCard>();
    }

    // Mazo francés de 52 cartas: id 1..52 (nombre del .png), valores 2..14 (As = 14)
    public static List<StdCard> buildStandardDeck() {
        List<StdCard> cards = new ArrayList<>();
        int id = 1;
        for (String suit : SUITS) {
            for (int value = 2; value <= 14; value++) {
                cards.add(new StdCard(id++, value, suit));
            }
        }
        return cards;
    }

    public BlackjackPlayer getDealer() {
        return dealer;
    }

    public Deck<StdCard> getDiscardDeck() {
        return discardDeck;
    }

    // Roba una carta; si el mazo se agotó, recicla el descarte
    public StdCard draw() {
        if (drawDeck.isEmpty()) {
            recycleDiscard();
        }
        return drawDeck.takeCard();
    }

    private void recycleDiscard() {
        while (!discardDeck.isEmpty()) {
            drawDeck.addBottom(discardDeck.takeCard());
        }
        drawDeck.shuffle();
    }

    // Todas las cartas (jugadores y banca) vuelven al mazo de robo
    public void collectCards() {
        for (BlackjackPlayer player : players) {
            for (StdCard card : player.resetRound()) {
                discardDeck.addBottom(card);
            }
        }
        for (StdCard card : dealer.resetRound()) {
            discardDeck.addBottom(card);
        }
        recycleDiscard();
    }

    // Reparto: una carta a cada jugador, una a la banca, otra vuelta igual
    // (la segunda carta de la banca es la que queda tapada)
    public @Override void dealAllCards() {
        for (int round = 0; round < 2; round++) {
            for (BlackjackPlayer player : players) {
                StdCard card = draw();
                if (card != null) {
                    player.addCardtoHand(card);
                }
            }
            StdCard card = draw();
            if (card != null) {
                dealer.addCardtoHand(card);
            }
        }
    }

    public @Override void setGame() {
        collectCards();
        dealAllCards();
    }
}
