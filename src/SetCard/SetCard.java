package SetCard;

import Card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class SetCard {
    protected List<Card> cards = new ArrayList<>();

    public int getAmount(){
        return cards.size();
    }

    public Card peekSearchCard(int id){
        for (Card card:cards){
            if (id == card.getId()){
                return card;
            }
        }
        System.out.println("Warning (PeekSearchCard): id not in Set");
        return null;
    }

    public Card takeSearchCard(int id){
        for (int i = 0; i < cards.size(); i++ ) {
            if (id == cards.get(i).getId()){
                return cards.remove(i);
            }
        }
        System.out.println("Warning (TakeSearchCard): id not in Set");
        return null;
    }
    
}
