package SetCard;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import Card.Card;

public class Deck extends SetCard{

    public Card takeCard(){
        if (cards.isEmpty()){
            System.out.println("Warning (takeCard): Deck is empty");
            return null;
        }

        return cards.removeFirst();
    }
    
    public void shuffle(){
        Collections.shuffle(cards);
    }

    public List<Card> deal(int dealSize){
        if(dealSize < 0){
            System.err.println("Invalid argument");
            throw new IllegalArgumentException("handSize cannot be negative");
        }
        if(cards.size() < dealSize){
            System.out.println("Warning (Deal): Deck no Enough Cards");
            dealSize = cards.size();
        }
        List<Card> hand = new ArrayList<>(dealSize);
        for (int i = 0; i < dealSize; i++) {
            hand.add(takeCard());
        }
        return hand;
    }

    public void addTop(Card newCard){
        cards.addFirst(newCard);
    }

    public void addBottom(Card newCard){
        cards.addLast(newCard);
    }
}