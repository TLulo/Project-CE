package Player;

import java.util.ArrayList;
import java.util.List;

import Card.Card;
import SetCard.Hand;

public class StdPlayer<C extends Card> extends Player{
    protected Hand<C> hand;
    protected List<C> playerTable;

    public StdPlayer(int handSize){
        super();
        playerTable = new ArrayList<>();
        hand = new Hand<C>(handSize);
    }

    public void setPlayerHand(Hand<C> newHand){
        hand = newHand;
    }

    public Hand<C> getPlayerHand(){
        return hand;
    }

    public List<C> getPlayerTable(){
        return playerTable;
    }

    public void putCardinTable(int cardId){
         C card = hand.takeSearchCard(cardId);
         if (card != null) {
             playerTable.add(card);
         }
    }

    public C removeToTable(int CardId){
        for (int i = 0; i < playerTable.size(); i++ ) {
            if (playerTable.get(i).matchesId(CardId)){
                return playerTable.remove(i);
            }
        }
        System.out.println("Warning (removeToTable): id not in PlayerTable");
        return null;
    }
}
