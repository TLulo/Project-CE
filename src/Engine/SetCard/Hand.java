package Engine.SetCard;

import java.util.ArrayList;
import java.util.List;

import Engine.Card.Card;


public class Hand<C extends Card> extends SetCard<C>{
    private int handSize;

    public Hand(int handSize){
        if (handSize < 0) {
            System.err.println("Invalid argument");
            throw new IllegalArgumentException("handSize cannot be negative");
        }
        this.handSize = handSize;
    }

    // Get and Setter
    public void setMax(int max){
        if (max < 0) {
            System.err.println("Invalid argument");
            throw new IllegalArgumentException("handSize cannot be negative");
        }
        handSize = max;
    }

    public int getMax(){
        return handSize;
    }
    
    public void setHand(List<C> hand){
        setListCard(hand);
    }
    
    public @Override void setListCard(List<C> hand){
        if (hand.size() > handSize) {
            System.out.println("Warning (setHand): hand is too long");
            hand = new ArrayList<>(hand.subList(0, handSize));
        }else if (hand.size() < handSize) {
            System.out.println("Warning (setHand): hand is too litle");
        }
        cards = new ArrayList<>(hand);
    }

    //add methods
    public void add(C newCard){
        if (cards.size() < handSize){
            cards.addFirst(newCard);
        }else{
            System.out.println("Warning (add): hand is full");
        }
    }

}
