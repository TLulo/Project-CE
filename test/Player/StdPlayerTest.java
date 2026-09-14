package test.Player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import Card.Card;
import Card.StdCard;
import Player.StdPlayer;
import SetCard.Hand;

public class StdPlayerTest {
    @Test 
    void TestConstructor(){
        int handsize = 5;
        StdPlayer player = new StdPlayer(handsize);


        assertTrue(player.getPlayerHand().getMax() == handsize);
        assertTrue(player.getPlayerHand().isEmpty());
        assertTrue(player.getId() >= 0);
        assertTrue(player.getPlayerTable().isEmpty());

        assertNull(player.getName());
    }
    @Test
    void TestmatchesId(){
        StdPlayer player = new StdPlayer(0);
        
        int id = player.getId();

        assertTrue(player.matchesId(id));
        assertFalse(player.matchesId(id+1));
    }
    @Test
    void TestIdIncrement(){
        StdPlayer player1 = new StdPlayer(0);
        StdPlayer player2 = new StdPlayer(0);

        int id = player1.getId();

        assertEquals(id, player1.getId());
        assertEquals(id+1, player2.getId());
    }
    @Test 
    void TestSetAndGetName(){
        String name = "test";
        StdPlayer player = new StdPlayer(0);
        player.setName(name);
        assertEquals(name, player.getName());
    }
    @Test 
    void TestSetAndGetPlayerHand(){
        Hand hand = mock(Hand.class);
        StdPlayer player = new StdPlayer(0);
        player.setPlayerHand(hand);

        assertEquals(hand, player.getPlayerHand());
    }
    @Test 
    void TestPutCardinTable(){
        int idCard = 1;
        StdCard card = new StdCard(idCard, 0, "Test");

        StdPlayer player = new StdPlayer(1);
        player.getPlayerHand().add(card);
        player.putCardinTable(idCard);

        assertTrue(player.getPlayerHand().isEmpty());
        assertFalse(player.getPlayerTable().isEmpty());
        assertEquals(idCard, player.getPlayerTable().getFirst().getId());
    }
    @Test
    void TestPutCardinTableWithEmptyHand(){
        StdPlayer player = new StdPlayer(1);

        player.putCardinTable(1);

        assertTrue(player.getPlayerTable().isEmpty());
    }
    @Test
    void TestRemoveToTable(){
        Card card1 = new StdCard(0, 0, "Test 1");
        Card card2 = new StdCard(1, 0, "Test 2");

        StdPlayer player = new StdPlayer(2);
        player.getPlayerHand().add(card1);
        player.getPlayerHand().add(card2);

        player.putCardinTable(0);
        player.putCardinTable(1);

        Card newCard1 = player.removeToTable(0);
        Card newCard2 = player.removeToTable(1);

        assertEquals(card1, newCard1);
        assertEquals(card2, newCard2);

    }
    @Test
    void TestRemoveToTableWithoutId(){
        StdPlayer player = new StdPlayer(0);

        Card nullCard = player.removeToTable(2);

        assertNull(nullCard);
    }
}
