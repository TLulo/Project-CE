package SetCard;

import java.util.List;

import Card.Card;


public class Hand extends SetCard{
    private int handSize;

    public Hand(int handSize){
        this.handSize = handSize;
    }

    public void setMax(int max){
        handSize = max;
    }

    public int getMax(){
        return handSize;
    }

    public void setHand(List<Card> hand){
        if (hand.size() > handSize){
            System.out.println("Warning (setHand): hand is to long");
        }else{
            cards = hand;
        }
    }

    public void add(Card newCard){
        if (cards.size() < handSize){
            cards.addFirst(newCard);
        }else{
            System.out.println("Warning (add): hand is full");
        }
    }

}
