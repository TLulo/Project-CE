package Engine;

import Player.Player;
import Table.Table;

public class Engine {
    protected Table table;
    protected int currentTurn;

    public Engine(Table table){
        this.table = table;
        currentTurn = 0;
    }

    public Player getCurrentPlayer(){
        if (!table.getPlayers().isEmpty()){
            currentTurn = (currentTurn + 1) % table.getPlayers().size();
            return table.getPlayers().get(currentTurn);
        }
        System.out.println("Warning (getCurrentPlayer): no players at Table");
        return null;
    }

    public Table getTable(){
        return table;
    }
    
    public Player nextTurn(){
        if (!table.getPlayers().isEmpty()){
            currentTurn = (currentTurn + 1) % table.getPlayers().size();
            return getCurrentPlayer();
        }
        System.out.println("Warning (nextTurn): no players at Table");
        return null;
    }

    public void setGame(int handSize){
        table.setGame(handSize);
        currentTurn = 0;
    }
}