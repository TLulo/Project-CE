package UI.Hand;

import Engine.Card.Card;
import Engine.SetCard.Hand;
import UI.Card.CardUI;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class HandUI<C extends Card> extends StackPane{
    
    public HandUI(Hand<C> hand,Pos position,boolean showCards){
        HBox handview = new HBox(10);
        handview.setAlignment(position);

        for (C card : hand.getAllCards()) {
            handview.getChildren().add(new CardUI<C>(card,showCards));
        }   

        getChildren().add(handview);
    }
}
