package UI.Hand;

import Engine.Card.Card;
import Engine.SetCard.Hand;
import UI.Card.CardUI;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class HandUI<C extends Card> extends StackPane{
    private final Hand<C> hand;
    private final HBox handview;
    private final boolean showCards;

    public HandUI(Hand<C> hand,Pos position,boolean showCards){
        this.hand = hand;
        this.showCards = showCards;

        handview = new HBox(10);
        handview.setAlignment(position);

        getChildren().add(handview);

        refresh();
    }

    public void refresh(){
        handview.getChildren().clear();

        for (C card : hand.getAllCards()) {
            handview.getChildren().add(new CardUI<C>(card,showCards));
        }   
    }
}
