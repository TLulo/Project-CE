package UI.SetCard;

import java.util.List;
import java.util.function.Supplier;

import Engine.Card.Card;
import Engine.SetCard.Hand;
import UI.Card.CardUI;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class HandUI<C extends Card> extends StackPane {
    private final Supplier<Hand<C>> source;
    private final HBox handview;
    private final boolean showCards;
    private final double cardWidth;

    // Vista sobre una Hand fija, con el ancho de carta por defecto
    public HandUI(Hand<C> hand, boolean showCards) {
        this(() -> hand, showCards, CardUI.DEFAULT_WIDTH);
    }

    // Vista sobre una Hand que puede cambiar (p. ej. () -> player.getHand())
    public HandUI(Supplier<Hand<C>> source, boolean showCards, double cardWidth) {
        this.source = source;
        this.showCards = showCards;
        this.cardWidth = cardWidth;

        handview = new HBox(10);
        handview.setAlignment(Pos.CENTER);
        getChildren().add(handview);

        // Ocupa solo lo que miden las cartas; la posición la decide el padre
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);

        refresh();
    }

    public List<CardUI<C>> getCardViews() {
        return handview.getChildren().stream()
                .filter(CardUI.class::isInstance)
                .map(node -> (CardUI<C>) node)
                .toList();
    }

    public void refresh() {
        handview.getChildren().clear();

        Hand<C> hand = source.get();
        for (C card : hand.getAllCards()) {
            CardUI<C> cardUI = new CardUI<C>(card, showCards, cardWidth);
            handview.getChildren().add(cardUI);
        }
    }
}