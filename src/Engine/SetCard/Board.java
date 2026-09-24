package Engine.SetCard;

import Engine.Card.Card;

public class Board<C extends Card> extends SetCard<C>{
    //ADD Cards Methods
    public @Override void add(C card){
        cards.add(card);
    }

    public void add(C card, int index){
        cards.add(index, card);
    }
}
