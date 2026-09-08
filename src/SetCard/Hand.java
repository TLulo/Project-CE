package SetCard;

import Card.Card;


public class Hand extends SetCard{
    private int handSize;

    public void setMax(int max){
        handSize = max;
    }

    public int getMax(){
        return handSize;
    }

    public void add(Card newCard){
        cards.addFirst(newCard);
    }


}
