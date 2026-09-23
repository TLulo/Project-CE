package SetCard;

import Card.Card;

public class Board<C extends Card> extends SetCard<C>{
    public @Override void add(C card){
        cards.add(card);
    }

    public void add(C card, int index){
        cards.add(index, card);
    }
}
