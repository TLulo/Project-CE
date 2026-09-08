package SetCard;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Collections;

import Card.Card;

public class Deck extends SetCard{

    public Card takeCard(){
        if (cards.isEmpty()){
            System.out.println("Warning (takeCard): Deck is empty");
            return null;
        }

        return cards.remove(cards.size()-1);
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
            System.err.println("Not Enough Cards");
            throw new NoSuchElementException("Deck no Enough Cards");
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