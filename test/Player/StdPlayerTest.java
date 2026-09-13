package test.Player;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import Player.StdPlayer;
import SetCard.Hand;

public class StdPlayerTest {
    @Test 
    void TestConstructor(){
        int handsize = 5;
        StdPlayer player = new StdPlayer(handsize);


        assertTrue(player.getPlayerHand().getMax() == handsize);
        assertTrue(player.getId() >= 0);
        assertTrue(player.getPlayerTable().isEmpty());

        assertNull(player);
        assertNull(player.getPlayerTable());
        assertNull(player.getName());
        assertNull(player.getPlayerHand());
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
        assertNull(player.getName());
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

    }
}
