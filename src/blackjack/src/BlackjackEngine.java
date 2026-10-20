package blackjack.src.Engine;

import java.util.HashMap;
import java.util.Map;

import blackjack.src.Player.BlackjackPlayer;
import blackjack.src.Table.BlackjackTable;
import Engine.Card.StdCard;
import Engine.Engine.Engine;

/**
 * Reglas del Blackjack sobre el Engine genérico.
 * Turnos: cada jugador pide (hit) o se planta (stand); al terminar el último
 * juega la banca (pide hasta llegar a 17) y se calculan los resultados.
 */
public class BlackjackEngine extends Engine<BlackjackPlayer, StdCard, BlackjackTable> {
    public enum Result { BLACKJACK, WIN, PUSH, LOSE }

    public static final int DEALER_STAND = 17;

    private boolean roundOver = true;
    private final Map<Integer, Result> results = new HashMap<>();

    public BlackjackEngine(BlackjackTable table) {
        super(table);
    }

    public boolean isRoundOver() {
        return roundOver;
    }

    public BlackjackPlayer getDealer() {
        return table.getDealer();
    }

    // Nueva ronda: recoge cartas, mezcla, reparte y deja el turno en el primer jugador activo
    public void startRound() {
        results.clear();
        table.setGame();
        currentTurn = 0;
        roundOver = false;

        if (table.playersIsEmpty()) {
            System.out.println("Warning (startRound): no players at Table");
            finishRound();
            return;
        }
        if (getCurrentPlayer().isDone()) {
            advance();
        }
    }

    public @Override void setGame(int handSize) {
        startRound();
    }

    // Jugador actual pide carta
    public StdCard hit() {
        if (roundOver) {
            System.out.println("Warning (hit): round is over");
            return null;
        }
        BlackjackPlayer player = getCurrentPlayer();
        StdCard card = table.draw();
        if (player == null || card == null) {
            return null;
        }
        player.addCardtoHand(card);
        if (player.isDone()) {
            advance();
        }
        return card;
    }

    // Jugador actual se planta
    public void stand() {
        if (roundOver) {
            System.out.println("Warning (stand): round is over");
            return;
        }
        BlackjackPlayer player = getCurrentPlayer();
        if (player == null) {
            return;
        }
        player.stand();
        advance();
    }

    // Pasa al siguiente jugador que aún pueda actuar; si no queda ninguno, juega la banca
    private void advance() {
        while (true) {
            nextTurn();
            if (currentTurn == 0) {
                finishRound();
                return;
            }
            if (!getCurrentPlayer().isDone()) {
                return;
            }
        }
    }

    private void finishRound() {
        BlackjackPlayer dealer = table.getDealer();

        boolean anyAlive = false;
        for (BlackjackPlayer player : table.getPlayers()) {
            if (!player.isBust()) {
                anyAlive = true;
            }
        }
        if (anyAlive) {
            while (dealer.getScore() < DEALER_STAND) {
                StdCard card = table.draw();
                if (card == null) {
                    break;
                }
                dealer.addCardtoHand(card);
            }
        }
        dealer.stand();

        for (BlackjackPlayer player : table.getPlayers()) {
            results.put(player.getId(), evaluate(player, dealer));
        }
        roundOver = true;
    }

    private Result evaluate(BlackjackPlayer player, BlackjackPlayer dealer) {
        if (player.isBust()) {
            return Result.LOSE;
        }
        if (player.isBlackjack()) {
            return dealer.isBlackjack() ? Result.PUSH : Result.BLACKJACK;
        }
        if (dealer.isBlackjack()) {
            return Result.LOSE;
        }
        if (dealer.isBust()) {
            return Result.WIN;
        }
        int diff = Integer.compare(player.getScore(), dealer.getScore());
        if (diff > 0) {
            return Result.WIN;
        }
        return diff == 0 ? Result.PUSH : Result.LOSE;
    }

    // Resultado de un jugador (null mientras la ronda no terminó)
    public Result getResult(BlackjackPlayer player) {
        if (!roundOver) {
            System.out.println("Warning (getResult): round is not over");
            return null;
        }
        return results.get(player.getId());
    }
}
