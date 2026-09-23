package Engine;

import Card.Card;
import Player.Player;
import Table.Table;

public class Engine <P extends Player,C extends Card, T extends Table<P,C>>{
    protected T table;
    protected int currentTurn;

    public Engine(T table){
        this.table = table;
        currentTurn = 0;
    }

    public P getCurrentPlayer(){
        if (!table.playersIsEmpty()){
            currentTurn = (currentTurn) % table.playersNumber();
            return table.getPlayers().get(currentTurn);
        }
        System.out.println("Warning (getCurrentPlayer): no players at Table");
        return null;
    }

    public T getTable(){
        return table;
    }
    
    public P nextTurn(){
        if (!table.playersIsEmpty()){
            currentTurn = (currentTurn + 1) % table.playersNumber();
            return getCurrentPlayer();
        }
        System.out.println("Warning (nextTurn): no players at Table");
        return null;
    }

    public void setGame(int handSize){
        currentTurn = 0;
    }
}