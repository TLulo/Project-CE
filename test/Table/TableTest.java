package test.Table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Card.StdCard;
import Player.Player;
import Player.StdPlayer;
import SetCard.Deck;
import Table.Table;
import Table.StdTable;

public class TableTest {
    @Test
    void ConstructorTest(){
        List<StdCard> deck = new ArrayList<StdCard>();

        deck.add(new StdCard(1, 0, null));
        deck.add(new StdCard(2, 0, null));
        Table<StdPlayer<StdCard>,StdCard> table = new StdTable(deck);

        assertFalse(table.getDrawDeck().isEmpty());
        assertTrue(table.getPlayers().isEmpty());
    }
    @Test
    void ConstructorNullTest(){
        Table<StdPlayer<StdCard>,StdCard> table = new StdTable(null);

        assertTrue(table.getDrawDeck().isEmpty());
        assertTrue(table.getPlayers().isEmpty());
    }
    @Test 
    void allAddnDrawTest(){
        StdCard card3 = new StdCard(3, 0, null);
        StdCard card4 = new StdCard(4, 0, null);

        Table<StdPlayer<StdCard>,StdCard> table = new StdTable(new ArrayList<>());

        assertEquals(0, table.getDrawDeck().getAmount());
        table.addDrawCard(new StdCard(1, 0, null));
        table.addDrawCard(new StdCard(2, 0, null));
        assertEquals(2, table.getDrawDeck().getAmount());
        
        table.addLastDraw(card3);

        table.addNextDraw(card4);

        assertEquals(4, table.getDrawDeck().getAmount());

        assertEquals(card4, table.drawCard());

        table.drawCard();
        table.drawCard();
        assertEquals(1, table.getDrawDeck().getAmount());

        assertEquals(card3, table.drawCard());
    }
    @Test 
    void addnGetPlayerTest(){
        StdPlayer<StdCard> player1 = new StdPlayer<StdCard>(0);
        StdPlayer<StdCard> player2 = new StdPlayer<StdCard>(0);

        Table<StdPlayer<StdCard>,StdCard> table = new StdTable(new ArrayList<>());

        table.addPlayer(player1);
        assertEquals(1, table.getPlayers().size());
        table.addPlayer(player2);
        assertEquals(2, table.getPlayers().size());
    }
    @Test
    void getOnePlayerTest(){
        StdPlayer<StdCard> player1 = new StdPlayer<StdCard>(0);
        StdPlayer<StdCard> player2 = new StdPlayer<StdCard>(0);

        Table<StdPlayer<StdCard>,StdCard> table = new StdTable(new ArrayList<>());

        table.addPlayer(player1);
        table.addPlayer(player2);

        assertEquals(player2, table.getOnePlayer(player2.getId()));
        assertEquals(player1, table.getOnePlayer(player1.getId()));
    }
    @Test 
    void deletePlayerTest(){
        StdPlayer<StdCard> player1 = new StdPlayer<StdCard>(0);
        StdPlayer<StdCard> player2 = new StdPlayer<StdCard>(0);

        Table<StdPlayer<StdCard>,StdCard> table = new StdTable(new ArrayList<>());

        table.addPlayer(player1);
        table.addPlayer(player2);

        assertEquals(player2, table.deletePlayer(player2.getId()));
        assertEquals(player1, table.deletePlayer(player1.getId()));
        assertTrue(table.getPlayers().isEmpty());
    }
    @Test 
    void getnDeletePlayerNOTIDTest(){
        int magicNumber = 55;
        StdPlayer<StdCard> player1 = new StdPlayer<StdCard>(0);
        StdPlayer<StdCard> player2 = new StdPlayer<StdCard>(0);

        Table<StdPlayer<StdCard>,StdCard> table = new StdTable(new ArrayList<>());

        table.addPlayer(player1);
        table.addPlayer(player2);

        assertEquals(null, table.getOnePlayer(player2.getId() + magicNumber));
        assertEquals(null, table.getOnePlayer(player1.getId() + magicNumber));

        assertEquals(null, table.deletePlayer(player2.getId() + magicNumber));
        assertEquals(null, table.deletePlayer(player1.getId()+ magicNumber));
        assertEquals(2, table.getPlayers().size());
    }
}
