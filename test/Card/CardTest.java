package test.Card;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import Engine.Card.Card;
import Engine.Card.StdCard;

public class CardTest {
    @Test 
    void TestConstructor(){
        int id = 4;
        Card card = new StdCard(id, 0, null);

        assertEquals(id, card.getId());
    }

    @Test 
    void TestMatchesId(){
        int id = 4;
        Card card = new StdCard(id, 0, null);

        assertTrue(card.matchesId(id));
        assertFalse(card.matchesId(id+1));
    }
    
}
