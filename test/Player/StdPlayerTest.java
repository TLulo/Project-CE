package test.Player;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Card.Card;
import Card.StdCard;
import Player.StdPlayer;

public class StdPlayerTest {
    @Test 
    void TestConstructor(){
        int handsize = 5;
        StdPlayer<StdCard> player = new StdPlayer<StdCard>(handsize);


        assertTrue(player.getHandCapacity() == handsize);
        assertTrue(player.getPlayerHand().isEmpty());

        assertTrue(player.getId() >= 0);

        assertTrue(player.getPlayerTable().isEmpty());

        assertNull(player.getName());
    }
    @Test 
    void TestSetAndGetPlayerHand(){
        List<StdCard> hand = new ArrayList<StdCard>();
        hand.add(new StdCard(1, 0, null));
        hand.add(new StdCard(2, 0, null));
        hand.add(new StdCard(3, 0, null));
        StdPlayer<StdCard> player1 = new StdPlayer<StdCard>(2);
        StdPlayer<StdCard> player2 = new StdPlayer<StdCard>(3);
        player1.setPlayerHand(hand);
        player2.setPlayerHand(hand);

        assertNotEquals(hand, player1.getPlayerHand());
        assertEquals(hand, player2.getPlayerHand());
    }
    @Test 
    void TestPutCardinTable(){
        int idCard = 1;
        StdCard card = new StdCard(idCard, 0, "Test");

        StdPlayer<StdCard> player = new StdPlayer<StdCard>(1);
        player.addCardtoHand(card);
        player.putCardinTable(idCard);

        assertTrue(player.getPlayerHand().isEmpty());
        assertFalse(player.getPlayerTable().isEmpty());
        assertEquals(idCard, player.getPlayerTable().getFirst().getId());
    }
    @Test
    void TestPutCardinTableWithEmptyHand(){
        StdPlayer<StdCard> player = new StdPlayer<StdCard>(1);

        player.putCardinTable(1);

        assertTrue(player.getPlayerTable().isEmpty());
    }
    @Test
    void TestRemoveToTable(){
        Card card0 = new StdCard(0, 0, "Test 1");
        Card card1 = new StdCard(1, 0, "Test 2");
        Card card2 = new StdCard(2, 0, "Test 3");

        StdPlayer<Card> player = new StdPlayer<Card>(3);
        
        player.addCardtoHand(card0);
        player.addCardtoHand(card1);
        player.addCardtoHand(card2);

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
        StdPlayer<Card> player = new StdPlayer<Card>(0);

        Card nullCard = player.removeToTable(2);

        assertNull(nullCard);
    }
}
