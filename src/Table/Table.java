package Table;

import java.util.ArrayList;
import java.util.List;

import Player.Player;

public abstract class Table<T extends Player> {
    protected  List<T> players = new ArrayList<>();

    /*
     * SubClases should be implemented setGame
     * with necesary Decks and other stuffs 
     */
    public abstract void setGame(int handSize);

    /**************************************** */
    //Player Methods
    public void addPlayer(T newPlayer){
        players.add(newPlayer);
    }

    public List<T> getPlayers(){
        return players;
    }
    
    public T getOnePlayer(int idPlayer){
        for (T player : players) {
            if (player.matchesId(idPlayer)){
                return player;
            }
        }
        System.out.println("Warning (getOnePlayer): id not in Players");
        return null;
    }

    public T deletePlayer(int idPlayer){
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).matchesId(idPlayer)){
                return players.remove(i);
            }
        }
        System.out.println("Warning (DeletePlayer): id not in Players");
        return null;
    }

}