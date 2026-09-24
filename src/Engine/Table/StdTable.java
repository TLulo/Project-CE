package Engine.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Engine.Card.StdCard;
import Engine.Player.StdPlayer;
import Engine.SetCard.Board;
import Engine.SetCard.Deck;

public class StdTable extends Table<StdPlayer<StdCard>, StdCard>{
    private Deck<StdCard> discardDeck;
    private Board<StdCard> inBoard;
    
    public StdTable(List<StdCard> deck){
        super(deck);
        discardDeck = new Deck<StdCard>();
        inBoard = new Board<StdCard>();
    }

    public Deck<StdCard> getDiscardDeck(){
        return discardDeck;
    }

    public List<StdCard> getBoard(){
        return Collections.unmodifiableList(inBoard.getAllCards());
    }

    public @Override void dealAllCards(){
        for (StdPlayer<StdCard> player : players) {
            player.setPlayerHand(drawDeck.deal(player.getHandCapacity()));
        }
    }

    public void convertDiscarInDraw(){
        int bound = discardDeck.getAmount();
        for (int i = 0; i < bound; i++) {
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
        inBoard = new Board<StdCard>();
    }

    public void boardToDiscard(){
        for (StdCard card : getBoard()) {
            discardDeck.addBottom(card);
        }
        resetBoard();
    }

    public void boardToDrawDeck(){
        for (StdCard card : getBoard()) {
            drawDeck.addBottom(card);
        }
        resetBoard();
    }

    public @Override void setGame(){
        dealAllCards();
    }
    
}
