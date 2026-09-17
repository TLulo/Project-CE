package Table;

import Player.Player;
import Player.StdPlayer;
import SetCard.Deck;

public class StdTable extends Table<StdPlayer>{
    private Deck drawDeck;

    public StdTable(){
        super();
        drawDeck = new Deck();
    }

    public @Override void setGame(int handSize){
        for (StdPlayer player : players) {
            player.setPlayerHand(drawDeck.deal(handSize));
        }
    }
}
