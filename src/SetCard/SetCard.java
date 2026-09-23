package SetCard;

import Card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class SetCard <C extends Card>{
    protected List<C> cards = new ArrayList<>();

    public int getAmount(){
        return cards.size();
    }
    public void setListCard(List<C> cards){
        this.cards = new ArrayList<>(cards);
    }
    public List<C> getAllCards(){
        return cards;
    }

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

    public boolean isEmpty(){
        return cards.isEmpty();
    }

    public abstract void add(C card);
    
}
