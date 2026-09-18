package test.Card;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Card.StdCard;

public class StdCardTest {
    @Test 
    void TestConstructor(){
        int id = 4;
        int value = 7;
        String suit = "trebol";
        StdCard card = new StdCard(id, value, suit);

        assertEquals(id, card.getId());
        assertEquals(value, card.getValue());
        assertEquals(suit, card.getSuit());
    }
    @Test
    void TestGetValue(){
        int id = 4;
        int value = 7;
        String suit = "trebol";
        StdCard card = new StdCard(id, value, suit);

        assertEquals(value, card.getValue());
    }
    @Test 
    void TestGetSuit(){
        int id = 4;
        int value = 7;
        String suit = "trebol";
        StdCard card = new StdCard(id, value, suit);

        assertEquals(suit, card.getSuit());
    }
}
