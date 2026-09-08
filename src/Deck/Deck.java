import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Collections;

import Card.Card;

public class Deck{
    private List<Card> cards;

    public int getAmount(){
        return cards.size();
    }

    //Standar Game (Poker, Truco,...)
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

    public Card peekSearchCard(int id){
        for (Card card:cards){
            if (id == card.getId()){
                return card;
            }
        }
        System.out.println("Warning (PeekSearchCard): id not in Deck");
        return null;
    }

    public Card takeSearchCard(int id){
        for (int i = 0; i < cards.size(); i++ ) {
            if (id == cards.get(i).getId()){
                return cards.remove(i);
            }
        }
        System.out.println("Warning (PeekSearchCard): id not in Deck");
        return null;
    }

    public void addTop(Card newCard){
        cards.addFirst(newCard);
    }

    public void addBottom(Card newCard){
        cards.addLast(newCard);
    }
}