package test.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

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
        Card card0 = new StdCard(0, 0, "Test 1");
        Card card1 = new StdCard(1, 0, "Test 2");
        Card card2 = new StdCard(2, 0, "Test 3");

        StdPlayer player = new StdPlayer(3);
        player.getPlayerHand().add(card0);
        player.getPlayerHand().add(card1);
        player.getPlayerHand().add(card2);

        player.putCardinTable(0);
        player.putCardinTable(1);
        player.putCardinTable(2);

        Card newCard2 = player.removeToTable(2);
        Card newCard1 = player.removeToTable(1);
        Card newCard0 = player.removeToTable(0);

        assertEquals(card0, newCard0);
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
