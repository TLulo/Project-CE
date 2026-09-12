package Table;

import java.util.ArrayList;
import java.util.List;

import Player.Player;

public abstract class Table {
    protected  List<Player> players = new ArrayList<>();

    /*
     * SubClases should be implemented setGame
     * with necesary Decks and other stuffs 
     */
    public abstract void setGame(int handSize);

    /**************************************** */
    //Player Methods
    public void addPlayer(Player newPlayer){
        players.add(newPlayer);
    }

    public List<Player> getPlayers(){
        return players;
    }
    
    public Player getOnePlayer(int idPlayer){
        for (Player player : players) {
            if (player.matchesId(idPlayer)){
                return player;
            }
        }
        System.out.println("Warning (getOnePlayer): id not in Players");
        return null;
    }

    public Player deletePlayer(int idPlayer){
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).matchesId(idPlayer)){
                return players.remove(i);
            }
        }
        System.out.println("Warning (DeletePlayer): id not in Players");
        return null;
    }

}