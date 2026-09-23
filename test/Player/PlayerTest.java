package test.Player;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import Player.Player;
import Player.StdPlayer;
import Card.StdCard;
import Card.Card;

public class PlayerTest {
    @Test 
    void TestConstructor(){
        Player player = new StdPlayer<Card>(0);

        assertTrue(player.getId() >= 0);
        assertNull(player.getName());
    }
    @Test
    void TestmatchesId(){
        Player player = new StdPlayer<Card>(0);
        
        int id = player.getId();

        assertTrue(player.matchesId(id));
        assertFalse(player.matchesId(id+1));
    }
    @Test
    void TestIdIncrement(){
        Player player1 = new StdPlayer<Card>(0);
        Player player2 = new StdPlayer<Card>(0);

        int id = player1.getId();

        assertEquals(id, player1.getId());
        assertEquals(id+1, player2.getId());
    }
    @Test 
    void TestSetAndGetName(){
        String name = "test";
        Player player = new StdPlayer<Card>(0);
        player.setName(name);
        assertEquals(name, player.getName());
    }
}
