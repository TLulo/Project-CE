package Player;

import java.util.ArrayList;
import java.util.List;

import Card.Card;
import SetCard.Hand;

public class Player {
    protected static int nextId = 0;
    protected int playerId;
    protected Hand hand;
    protected List<Card> playerTable;
    protected String name;

    public Player(){
        playerTable = new ArrayList<>();
        playerId = nextId ++;
    }

    public int getId(){
        return playerId;
    }

    public boolean matchesId(int ID){
        return getId() == ID;
    }

    public void setPlayerHand(Hand newHand){
        hand = newHand;
    }

    public Hand getPlayerHand(){
        return hand;
    }

    public void setName(String newName){
        name = newName;
    }

    public String getName(){
        return name;
    }

    public int getPlayerId(){
        return playerId;
    }

    public void putCardinTable(int cardId){
        playerTable.add(hand.takeSearchCard(cardId));
    }

    public Card removeToTable(int CardId){
        for (int i = 0; i < playerTable.size(); i++ ) {
            if (playerTable.get(i).matchesId(CardId)){
                return playerTable.remove(i);
            }
        }
        System.out.println("Warning (TakeSearchCard): id not in Table");
        return null;
    }

}
