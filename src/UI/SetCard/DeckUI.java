package UI.SetCard;

import Engine.Card.Card;
import Engine.SetCard.Deck;
import UI.Card.CardUI;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

public class DeckUI<C extends Card> extends StackPane {

    private final Deck<C> deck;
    private final boolean showFirst;
    private final StackPane deckBox;

    public DeckUI(Deck<C> deck, boolean showFirst, Pos position) {
        this.deck = deck;
        this.showFirst = showFirst;

        deckBox = new StackPane();
        deckBox.setAlignment(position);

        getChildren().add(deckBox);

        refresh();
    }

    public void refresh() {
        int count = 0;
        deckBox.getChildren().clear();

        for (int i = 0; i < deck.getAmount(); i++) {

            CardUI<C> card = new CardUI<>(null, false);

            card.setTranslateX(i * 1);
            card.setTranslateY(i * -1.5);

            deckBox.getChildren().add(card);
            count ++;
        }

        if (showFirst && !deck.isEmpty()) {
            CardUI<C> card = new CardUI<>(deck.peekFirstCard(), true);
            card.setTranslateX(count * 1);
            card.setTranslateY(count * -1.5);
            deckBox.getChildren().add(card);
        }
    }
}