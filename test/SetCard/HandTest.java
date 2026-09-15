import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Card.Card;
import Card.StdCard;
import SetCard.Hand;

public class HandTest {
    @Test 
    void TestConstructor(){
        int handsize = 5;
        Hand hand = new Hand(handsize);

        assertEquals(5, hand.getMax());
        assertTrue(hand.getAllCards().isEmpty());
    }
    @Test 
    void TestConstructorNegative(){
        int handsize = -5;
        
        assertThrowsExactly(IllegalArgumentException.class, ()->new Hand(handsize));
    }
    @Test 
    void TestSetnGetMax(){
        int handsize = 5;
        Hand hand = new Hand(handsize);

        assertEquals(handsize, hand.getMax());

        handsize = 7;
        hand.setMax(handsize);

        assertEquals(handsize, hand.getMax());
    }
    @Test 
    void TestSetMaxNegative(){
        int handsize = 5;
        Hand hand = new Hand(handsize);
        
        assertThrowsExactly(IllegalArgumentException.class, ()->hand.setMax(-5));
    }
    @Test 
    void TestSetnGetListCard(){
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");
        Card card3 = new StdCard(3, 0, "test");

        List<Card> listCards = new ArrayList<>();
        listCards.add(card1);
        listCards.add(card2);
        listCards.add(card3);

        Hand hand = new Hand(3);

        assertTrue(hand.getAllCards().isEmpty());

        hand.setListCard(listCards);
        assertEquals(listCards, hand.getAllCards());
    }
    @Test 
    void TestSetnGetListCardLittleList(){
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");
        Card card3 = new StdCard(3, 0, "test");

        List<Card> listCards = new ArrayList<>();
        listCards.add(card1);
        listCards.add(card2);
        listCards.add(card3);

        Hand hand = new Hand(999);

        assertTrue(hand.getAllCards().isEmpty());

        hand.setListCard(listCards);
        assertEquals(listCards, hand.getAllCards());
        assertEquals(999, hand.getMax());
    }
    @Test 
    void TestSetnGetListCardBigList(){
        int handSize = 2;
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");
        Card card3 = new StdCard(3, 0, "test");

        List<Card> listCards = new ArrayList<>();
        listCards.add(card1);
        listCards.add(card2);
        listCards.add(card3);

        Hand hand = new Hand(handSize);

        assertTrue(hand.getAllCards().isEmpty());

        hand.setListCard(listCards);
        assertEquals(listCards.subList(0, handSize), hand.getAllCards());
    }
    @Test 
    void Testadd(){
        int handSize = 2;
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");
        Card card3 = new StdCard(3, 0, "test");

        Hand hand = new Hand(handSize);
        assertTrue(hand.isEmpty());
        hand.add(card3);

        assertEquals(1, hand.getAmount());
        
        hand.add(card2);
        assertEquals(2, hand.getAmount());

        hand.add(card1);
        assertEquals(2, hand.getAmount());
    }
}
