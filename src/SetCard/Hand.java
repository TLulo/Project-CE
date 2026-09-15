package SetCard;

import java.util.ArrayList;
import java.util.List;

import Card.Card;


public class Hand extends SetCard{
    private int handSize;

    public Hand(int handSize){
        if (handSize < 0) {
            System.err.println("Invalid argument");
            throw new IllegalArgumentException("handSize cannot be negative");
        }
        this.handSize = handSize;
    }

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

    public @Override void setListCard(List<Card> hand){
        if (hand.size() > handSize) {
            System.out.println("Warning (setHand): hand is too long");
            hand = new ArrayList<>(hand.subList(0, handSize));
        }else if (hand.size() < handSize) {
            System.out.println("Warning (setHand): hand is too litle");
        }
        cards = new ArrayList<>(hand);
    }

    public void add(Card newCard){
        if (cards.size() < handSize){
            cards.addFirst(newCard);
        }else{
            System.out.println("Warning (add): hand is full");
        }
    }

}
