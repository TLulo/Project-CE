package Table;

import java.util.ArrayList;
import java.util.List;

import Card.Card;
import Player.Player;
import SetCard.Deck;

public class StdTable<P extends Player, C extends Card> extends Table<P>{
    private Deck<C> drawDeck = new Deck<C>();
    private Deck<C> discardDeck = new Deck<C>();
    private List<C> inBoard = new ArrayList<>();
    
    public StdTable(List<C> deck){
        drawDeck.setListCard(deck);
    }

    public void discardCard(C card){
        discardDeck.addTop(card);
    }

    public void putCardinBoard(C card){
        inBoard.add(card);
    }

    public void addDrawDeck(C card){
        drawDeck.addBottom(card);
    }

    public void addNextDraw(C card){
        drawDeck.addTop(card);
    }

    public void setGame(int handSize, Deck<C> draw){

    }
    
}
