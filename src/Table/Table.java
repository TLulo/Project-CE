package Table;

import java.util.ArrayList;
import java.util.List;

import Card.Card;
import Player.Player;
import SetCard.Deck;

public abstract class Table<P extends Player, C extends Card> {
    protected  List<P> players = new ArrayList<>();
    protected Deck<C> drawDeck = new Deck<C>();

    public Table(List<C> deck){
        if(deck instanceof List<C>){
            drawDeck.setListCard(deck);
        }
    }

    /*
     * SubClases should be implemented setGame
     * with necesary Decks and other stuffs 
     */

    public abstract void setGame();

    /**************************************** */
    //Draw Deck Methods
    public Deck<C> getDrawDeck(){
        return drawDeck;
    }

    public void addLastDraw(C card){
        drawDeck.addBottom(card);
    }

    public void addNextDraw(C card){
        drawDeck.addTop(card);
    }

    public void addDrawCard(C card){
        drawDeck.addRandom(card);
    }

    public C drawCard(){
        return drawDeck.takeCard();
    }

    public abstract void dealAllCards();

    //Player Methods
    public void addPlayer(P newPlayer){
        players.add(newPlayer);
    }

    public List<P> getPlayers(){
        return players;
    }
    
    public P getOnePlayer(int idPlayer){
        for (P player : players) {
            if (player.matchesId(idPlayer)){
                return player;
            }
        }
        System.out.println("Warning (getOnePlayer): id not in Players");
        return null;
    }

    public P deletePlayer(int idPlayer){
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).matchesId(idPlayer)){
                return players.remove(i);
            }
        }
        System.out.println("Warning (DeletePlayer): id not in Players");
        return null;
    }

}