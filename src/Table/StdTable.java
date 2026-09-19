package Table;

import java.util.ArrayList;
import java.util.List;

import Card.Card;
import Card.StdCard;
import Player.Player;
import Player.StdPlayer;
import SetCard.Deck;
import SetCard.Hand;

public class StdTable extends Table<StdPlayer<StdCard>, StdCard>{
    private Deck<StdCard> discardDeck;
    private List<StdCard> inBoard;
    
    public StdTable(List<StdCard> deck){
        super(deck);
        discardDeck = new Deck<StdCard>();
        inBoard = new ArrayList<>();
    }

    public @Override void DealAllCards(){
        for (StdPlayer<StdCard> player : players) {
            player.setPlayerHand(drawDeck.deal(player.getPlayerHand().getMax()));
        }
    }

    public void convertDiscarInDraw(){
        for (int i = 0; i < discardDeck.getAmount(); i++) {
            drawDeck.addBottom(discardDeck.takeCard());
        }
    }

    public void discardCard(StdCard card){
        discardDeck.addTop(card);
    }

    public StdCard takeDiscard(){
        return discardDeck.takeCard();
    }

    public StdCard takeRandomDiscard(){
        return discardDeck.takeRandomCard();
    }

    public void putCardinBoard(StdCard card){
        inBoard.add(card);
    }

    public void setGame(int handSize, Deck<StdCard> draw){

    }
    
}
