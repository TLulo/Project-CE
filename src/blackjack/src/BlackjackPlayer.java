package blackjack.src.Player;

import java.util.ArrayList;
import java.util.List;

import Engine.Card.StdCard;
import Engine.Player.StdPlayer;

/**
 * Jugador de Blackjack (también se usa para la banca).
 * Toda la lógica de puntaje vive aquí, no en StdCard.
 * StdCard usa valores 2-14 (As = 14): 2-10 valen su número,
 * 11-13 (J, Q, K) valen 10 y el As vale 11 o 1.
 */
public class BlackjackPlayer extends StdPlayer<StdCard> {
    // Con 11 cartas es imposible no pasarse de 21 (4 ases + 4 doses + 3 treses = 21)
    public static final int MAX_HAND = 12;
    public static final int BLACKJACK = 21;

    private static final int ACE = 14;

    private boolean standing = false;

    public BlackjackPlayer() {
        super(MAX_HAND);
    }

    // Puntos de una carta contando el As como 11
    public static int cardPoints(StdCard card) {
        int value = card.getValue();
        if (value == ACE) {
            return 11;
        }
        return Math.min(value, 10);
    }

    // Mejor puntaje posible: los ases bajan de 11 a 1 mientras haya exceso
    public int getScore() {
        int total = 0;
        int aces = 0;
        for (StdCard card : getPlayerHand()) {
            total += cardPoints(card);
            if (card.getValue() == ACE) {
                aces++;
            }
        }
        while (total > BLACKJACK && aces > 0) {
            total -= 10;
            aces--;
        }
        return total;
    }

    public boolean isBust() {
        return getScore() > BLACKJACK;
    }

    // Blackjack "natural": 21 con las dos primeras cartas
    public boolean isBlackjack() {
        return getHandAmount() == 2 && getScore() == BLACKJACK;
    }

    public void stand() {
        standing = true;
    }

    public boolean isStanding() {
        return standing;
    }

    // Ya no puede actuar en esta ronda
    public boolean isDone() {
        return standing || getScore() >= BLACKJACK;
    }

    // Vacía la mano, reinicia el estado y devuelve las cartas para que la mesa las recoja
    public List<StdCard> resetRound() {
        List<StdCard> cards = new ArrayList<>(hand.getAllCards());
        hand.getAllCards().clear();
        standing = false;
        return cards;
    }
}
