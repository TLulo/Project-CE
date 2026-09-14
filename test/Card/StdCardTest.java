package test.Card;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import Card.Card;
import Card.StdCard;

public class StdCardTest {
    @Test 
    void testConstructor(){
        int id = 4;
        int value = 7;
        String suit = "trebol";
        StdCard card = new StdCard(id, value, suit);

        assertEquals(id, card.getId());
        assertEquals(value, card.getValue());
        assertEquals(suit, card.getSuit());
    }

    @Test 
    void testMatchesId(){
        int id = 4;
        int value = 7;
        String suit = "trebol";
        StdCard card = new StdCard(id, value, suit);

        assertTrue(card.matchesId(id));
        assertFalse(card.matchesId(id+1));
    }
    
}
