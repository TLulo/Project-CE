package blackjack.UI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import blackjack.src.Player.BlackjackPlayer;
import Engine.Card.StdCard;
import UI.Card.CardUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Vista de un participante de Blackjack (jugador o banca): título con nombre,
 * puntaje y estado, y sus cartas en orden de reparto.
 * Puede tapar la segunda carta (la de la banca) y resaltar al jugador con el turno.
 */
public class BlackjackPlayerUI extends VBox {
    private final Supplier<BlackjackPlayer> source;
    private final double cardWidth;

    private final Label caption = new Label();
    private final HBox cards = new HBox(10);

    public BlackjackPlayerUI(Supplier<BlackjackPlayer> source, double cardWidth) {
        super(8);
        this.source = source;
        this.cardWidth = cardWidth;

        setAlignment(Pos.CENTER);
        setPadding(new Insets(8));
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);

        caption.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15px;");
        cards.setAlignment(Pos.CENTER);
        cards.setMinHeight(cardWidth * 1.4);
        getChildren().addAll(caption, cards);

        refresh(false, false, "");
    }

    /**
     * @param hideSecond  tapa la segunda carta y oculta el puntaje (banca durante la ronda)
     * @param highlighted resalta con borde dorado (jugador con el turno)
     * @param info        texto extra tras el puntaje (BUST, stand, resultado...); puede ser ""
     */
    public void refresh(boolean hideSecond, boolean highlighted, String info) {
        BlackjackPlayer player = source.get();

        String name = player.getName() != null ? player.getName() : "Player " + player.getId();
        String text = hideSecond ? name : name + " (" + player.getScore() + ")";
        if (info != null && !info.isEmpty()) {
            text += " - " + info;
        }
        caption.setText(text);

        // La mano guarda la carta más nueva primero; se muestra en orden de reparto
        List<StdCard> ordered = new ArrayList<>(player.getPlayerHand());
        Collections.reverse(ordered);

        cards.getChildren().clear();
        for (int i = 0; i < ordered.size(); i++) {
            boolean faceUp = !(hideSecond && i == 1);
            cards.getChildren().add(new CardUI<StdCard>(ordered.get(i), faceUp, cardWidth));
        }

        setStyle(highlighted
                ? "-fx-border-color: gold; -fx-border-width: 2; -fx-border-radius: 8;"
                : "-fx-border-color: transparent; -fx-border-width: 2;");
    }
}
