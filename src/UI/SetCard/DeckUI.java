package UI.SetCard;

import java.util.function.Supplier;

import Engine.Card.Card;
import Engine.SetCard.Deck;
import UI.Card.CardUI;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Muestra una pila de cartas (mazo de robo, descarte, etc.) con un contador.
 * La carta de arriba es la que devuelve Deck.peekFirstCard().
 * Opcionalmente dibuja unas pocas capas desfasadas para simular el grosor.
 * La posición en pantalla la decide el padre.
 */
public class DeckUI<C extends Card> extends VBox {
    private static final int MAX_LAYERS = 8;
    private static final double OFFSET_X = 1.0;
    private static final double OFFSET_Y = -1.5;

    private final Supplier<Deck<C>> source;
    private final boolean faceUp;
    private final String title;
    private final double cardWidth;
    private final boolean showThickness;

    private final StackPane slot = new StackPane();
    private final Label caption = new Label();

    // Con el ancho por defecto y efecto de grosor
    public DeckUI(Supplier<Deck<C>> source, boolean faceUp, String title) {
        this(source, faceUp, title, CardUI.DEFAULT_WIDTH, true);
    }

    public DeckUI(Supplier<Deck<C>> source, boolean faceUp, String title,
                  double cardWidth, boolean showThickness) {
        super(6);
        this.source = source;
        this.faceUp = faceUp;
        this.title = title;
        this.cardWidth = cardWidth;
        this.showThickness = showThickness;

        setAlignment(Pos.CENTER);
        // Ocupa solo lo necesario; el padre decide dónde colocarlo
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);

        caption.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        getChildren().addAll(slot, caption);

        refresh();
    }

    public StackPane getSlot() {
        return slot;
    }

    public void refresh() {
        slot.getChildren().clear();
        Deck<C> deck = source.get();

        if (deck.isEmpty()) {
            Rectangle empty = new Rectangle(cardWidth, cardWidth * 1.4);
            empty.setArcWidth(15);
            empty.setArcHeight(15);
            empty.setFill(Color.TRANSPARENT);
            empty.setStroke(Color.web("#ffffff80"));
            empty.getStrokeDashArray().addAll(8.0, 6.0);
            slot.getChildren().add(empty);
        } else {
            C top = deck.peekFirstCard();
            int layers = showThickness ? Math.min(deck.getAmount(), MAX_LAYERS) : 1;

            // Capas de abajo: boca abajo, desfasadas para dar sensación de grosor
            for (int i = 0; i < layers - 1; i++) {
                CardUI<C> back = new CardUI<C>(top, false, cardWidth);
                back.setTranslateX(i * OFFSET_X);
                back.setTranslateY(i * OFFSET_Y);
                slot.getChildren().add(back);
            }

            // Carta de arriba: la última y la más desfasada
            CardUI<C> topView = new CardUI<C>(top, faceUp, cardWidth);
            topView.setTranslateX((layers - 1) * OFFSET_X);
            topView.setTranslateY((layers - 1) * OFFSET_Y);
            slot.getChildren().add(topView);
        }

        caption.setText(title + " (" + deck.getAmount() + ")");
    }
}