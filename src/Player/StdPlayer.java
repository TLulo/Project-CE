package Player;

import java.util.Collections;
import java.util.List;

import Card.Card;
import SetCard.Board;
import SetCard.Hand;

public class StdPlayer<C extends Card> extends Player{
    protected Hand<C> hand;
    protected Board<C> playerTable;

    public StdPlayer(int handSize){
        super();
        playerTable = new Board<C>();
        hand = new Hand<C>(handSize);
    }

    //HAND Methods
    public void setPlayerHand(List<C> newHand){
        hand.setHand(newHand);
    }

    public void setHandCapacity(int max){
        hand.setMax(max);
    }

    public int getHandCapacity(){
        return hand.getMax();
    }

    public List<C> getPlayerHand(){
        return Collections.unmodifiableList(hand.getAllCards());
    }

    public int getHandAmount(){
        return hand.getAmount();
    }

    public void addCardtoHand(C card){
        hand.add(card);
    }

    //Table Methods
    public List<C> getPlayerTable(){
        return Collections.unmodifiableList(playerTable.getAllCards());
    }

    public void putCardinTable(int cardId){
         C card = hand.takeSearchCard(cardId);
         if (card != null) {
             playerTable.add(card);
         }
    }

    public C removeToTable(int cardId){
        return playerTable.takeSearchCard(cardId);
    }
}
