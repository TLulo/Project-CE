package test.Table;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import Card.StdCard;
import Player.StdPlayer;
import Table.StdTable;

public class StdTableTest {
    @Test 
    void ConstructorTest(){
        StdTable table = new StdTable(null);

        assertTrue(table.getDrawDeck().isEmpty());
        assertTrue(table.getPlayers().isEmpty());
        assertTrue(table.getDiscardDeck().isEmpty());
        assertTrue(table.getBoard().isEmpty());
    }
    @Test 
    void dealAllCardsTest(){
        int handSize = 2;

        StdTable table = new StdTable(null);

        table.addPlayer(new StdPlayer<StdCard>(handSize));
        table.addPlayer(new StdPlayer<StdCard>(handSize));

        table.addDrawCard(new StdCard(1, 0, null));
        table.addDrawCard(new StdCard(2, 0, null));
        table.addDrawCard(new StdCard(3, 0, null));
        table.addDrawCard(new StdCard(4, 0, null));
        table.addDrawCard(new StdCard(5, 0, null));

        assertEquals(5, table.getDrawDeck().getAmount());

        table.dealAllCards();

        assertEquals(1, table.getDrawDeck().getAmount());

        for (StdPlayer<StdCard> player : table.getPlayers()) {
            assertEquals(2, player.getHandAmount());
        }
    }
    @Test 
    void setGameTest(){
        int handSize = 2;

        StdTable table = new StdTable(null);

        table.addPlayer(new StdPlayer<StdCard>(handSize));
        table.addPlayer(new StdPlayer<StdCard>(handSize));

        table.addDrawCard(new StdCard(1, 0, null));
        table.addDrawCard(new StdCard(2, 0, null));
        table.addDrawCard(new StdCard(3, 0, null));
        table.addDrawCard(new StdCard(4, 0, null));
        table.addDrawCard(new StdCard(5, 0, null));

        assertEquals(5, table.getDrawDeck().getAmount());

        table.setGame();

        assertEquals(1, table.getDrawDeck().getAmount());

        for (StdPlayer<StdCard> player : table.getPlayers()) {
            assertEquals(2, player.getHandAmount());
        }
    }
    @Test 
    void discardnTakeDiscardTest(){
        StdCard card1 = new StdCard(3, 0, null);
        StdCard card2 = new StdCard(4, 0, null);

        StdTable table = new StdTable(null);

        table.discardCard(card2);

        assertFalse(table.getDiscardDeck().isEmpty());

        table.discardCard(card1);

        assertEquals(2, table.getDiscardDeck().getAmount());

        assertEquals(card1, table.takeDiscard());
        assertEquals(card2, table.takeDiscard());
        assertTrue(table.getDiscardDeck().isEmpty());
    }
    @Test
    void convertDiscardInDrawTest(){
        StdCard card1 = new StdCard(3, 0, null);
        StdCard card2 = new StdCard(4, 0, null);

        StdTable table = new StdTable(null);

        table.discardCard(card2);
        table.discardCard(card1);

        table.convertDiscarInDraw();
        assertEquals(2, table.getDrawDeck().getAmount());
        assertTrue(table.getDiscardDeck().isEmpty());
    }
    @Test 
    void convertDiscardInDrawAmountTest(){
        StdCard card1 = new StdCard(3, 0, null);
        StdCard card2 = new StdCard(4, 0, null);
        StdCard card3 = new StdCard(5, 0, null);

        StdTable table = new StdTable(null);

        table.discardCard(card3);
        table.discardCard(card2);
        table.discardCard(card1);

        table.convertDiscarInDraw(2);
        assertEquals(2, table.getDrawDeck().getAmount());
        assertEquals(1, table.getDiscardDeck().getAmount());
    }
    @Test
    void takeRandomDiscardTest(){
        StdCard card1 = new StdCard(3, 0, null);
        StdCard card2 = new StdCard(4, 0, null);
        StdCard card3 = new StdCard(5, 0, null);

        StdTable table = new StdTable(null);

        table.discardCard(card3);
        table.discardCard(card2);
        table.discardCard(card1);

        assertEquals(3, table.getDiscardDeck().getAmount());
        table.takeRandomDiscard();
        assertEquals(2, table.getDiscardDeck().getAmount());
    }
    @Test 
    void putCardinBoardTest(){
        StdCard card1 = new StdCard(3, 0, null);
        StdTable table = new StdTable(null);

        table.putCardinBoard(card1);
        assertFalse(table.getBoard().isEmpty());
        assertEquals(card1, table.getBoard().getFirst());
    }
    @Test 
    void resetBoardTest(){
        StdCard card1 = new StdCard(3, 0, null);
        StdTable table = new StdTable(null);

        table.putCardinBoard(card1);
        assertFalse(table.getBoard().isEmpty());

        table.resetBoard();
        assertTrue(table.getBoard().isEmpty());
    }
    @Test
    void boardToDiscardTest(){
        StdCard card1 = new StdCard(3, 0, null);
        StdCard card2 = new StdCard(4, 0, null);
        
        StdTable table = new StdTable(null);

        assertTrue(table.getBoard().isEmpty());
        assertTrue(table.getDiscardDeck().isEmpty());

        table.putCardinBoard(card1);
        table.putCardinBoard(card2);
        assertFalse(table.getBoard().isEmpty());

        table.boardToDiscard();
        
        assertTrue(table.getBoard().isEmpty());
        assertFalse(table.getDiscardDeck().isEmpty());
    }
    @Test
    void boardToDrawDeckTest(){
        StdCard card1 = new StdCard(3, 0, null);
        StdCard card2 = new StdCard(4, 0, null);
        
        StdTable table = new StdTable(null);

        assertTrue(table.getBoard().isEmpty());
        assertTrue(table.getDrawDeck().isEmpty());

        table.putCardinBoard(card1);
        table.putCardinBoard(card2);
        assertFalse(table.getBoard().isEmpty());

        table.boardToDrawDeck();
        
        assertTrue(table.getBoard().isEmpty());
        assertFalse(table.getDrawDeck().isEmpty());
    }
}
