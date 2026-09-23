package SetCard;

import Card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class SetCard <C extends Card>{
    protected List<C> cards = new ArrayList<>();

    //GET and SET
    public void setListCard(List<C> cards){
        this.cards = new ArrayList<>(cards);
    }

    public List<C> getAllCards(){
        return cards;
    }

    // Utils
    public int getAmount(){
        return cards.size();
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    // Take and peek Cards
    public C peekSearchCard(int id){
        for (C card:cards){
            if (card.matchesId(id)){
                return card;
            }
        }
        System.out.println("Warning: id not in Set");
        return null;
    }

    public C takeSearchCard(int id){
        C card = peekSearchCard(id);
        cards.remove(card);
        return card;
    }

    //Add Methods
    public abstract void add(C card);
    
}
