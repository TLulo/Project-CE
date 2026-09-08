package SetCard;

import java.util.List;
import java.util.ArrayList;

import Card.Card;


public class Hand extends SetCard{
    private int handSize;

    public Hand(){
        handCards = new ArrayList<>();
    }

    public take

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
