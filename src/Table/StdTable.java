package Table;

import java.util.ArrayList;
import java.util.List;

import Card.StdCard;
import Player.StdPlayer;
import SetCard.Deck;

public class StdTable extends Table<StdPlayer<StdCard>, StdCard>{
    private Deck<StdCard> discardDeck;
    private List<StdCard> inBoard;
    
    public StdTable(List<StdCard> deck){
        super(deck);
        discardDeck = new Deck<StdCard>();
        inBoard = new ArrayList<>();
    }

    public @Override void dealAllCards(){
        for (StdPlayer<StdCard> player : players) {
            player.setPlayerHand(drawDeck.deal(player.getPlayerHand().getMax()));
        }
    }

    public void convertDiscarInDraw(){
        for (int i = 0; i < discardDeck.getAmount(); i++) {
            drawDeck.addBottom(discardDeck.takeCard());
        }
    }

    public void convertDiscarInDraw(int amount){
        for (int i = 0; i < amount; i++) {
            drawDeck.addBottom(discardDeck.takeRandomCard());
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

    public void resetBoard(){
        inBoard = new ArrayList<StdCard>();
    }

    public void boardToDiscard(){
        for (StdCard card : inBoard) {
            discardDeck.addBottom(card);
        }
        resetBoard();
    }

    public void boardToDrawDeck(){
        for (StdCard card : inBoard) {
            drawDeck.addBottom(card);
        }
        resetBoard();
    }

    public @Override void setGame(){
        dealAllCards();
    }
    
}
