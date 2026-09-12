package Player;

import java.util.ArrayList;
import java.util.List;

import Card.Card;
import SetCard.Hand;

public class StdPlayer extends Player {
    protected Hand hand;
    protected List<Card> playerTable;

    public StdPlayer(){
        super();
        playerTable = new ArrayList<>();
        hand = new Hand(new ArrayList<>());
    }

    public void setPlayerHand(Hand newHand){
        hand = newHand;
    }

    public Hand getPlayerHand(){
        return hand;
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
        System.out.println("Warning (TakeSearchCard): id not in PlayerTable");
        return null;
    }
}
