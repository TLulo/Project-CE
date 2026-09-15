package SetCard;

import Card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class SetCard {
    protected List<Card> cards = new ArrayList<>();

    public int getAmount(){
        return cards.size();
    }
    public void setListCard(List<Card> cards){
        for (Card card : cards) {
            this.cards.add(card);
        }
    }
    public List<Card> getAllCards(){
        return cards;
    }

    public Card peekSearchCard(int id){
        for (Card card:cards){
            if (card.matchesId(id)){
                return card;
            }
        }
        System.out.println("Warning: id not in Set");
        return null;
    }

    public Card takeSearchCard(int id){
        Card card = peekSearchCard(id);
        cards.remove(card);
        return card;
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }
    
}
