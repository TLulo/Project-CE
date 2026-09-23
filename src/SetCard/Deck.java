package SetCard;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import Card.Card;

public class Deck<C extends Card> extends SetCard<C>{

    //Take Cards methods
    public C takeCard(){
        if (cards.isEmpty()){
            System.out.println("Warning (takeCard): Deck is empty");
            return null;
        }

        return cards.removeFirst();
    }

    public C takeRandomCard(){
        if (cards.isEmpty()){
            return null;
        }
        return cards.remove(new Random().nextInt(0, getAmount()));
    }

    public List<C> deal(int dealSize){
        if(dealSize < 0){
            System.err.println("Invalid argument");
            throw new IllegalArgumentException("handSize cannot be negative");
        }
        if(cards.size() < dealSize){
            System.out.println("Warning (Deal): Deck no Enough Cards");
            dealSize = cards.size();
        }
        List<C> hand = new ArrayList<>(dealSize);
        for (int i = 0; i < dealSize; i++) {
            hand.add(takeCard());
        }
        return hand;
    }


    //Add Cards Methods
    public void addTop(C newCard){
        cards.addFirst(newCard);
    }

    public void addBottom(C newCard){
        cards.addLast(newCard);
    }

    public void add(C newCard){
        if(cards.isEmpty()){
            cards.add(newCard);
        }else{
            cards.add(new Random().nextInt(0, getAmount()), newCard);
        }
    }
}