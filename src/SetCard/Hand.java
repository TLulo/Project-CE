package SetCard;

import java.util.List;

import Card.Card;


public class Hand extends SetCard{
    private int handSize;

    public Hand(List<Card> newHand, int handSize){
        cards = newHand;
        this.handSize = handSize;
    }

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
