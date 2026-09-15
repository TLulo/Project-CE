import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Card.Card;
import Card.StdCard;
import SetCard.Deck;

public class SetCardTest {
    @Test 
    void TestConstructor(){
        Deck deck = new Deck();

        assertTrue(deck.isEmpty());
    }
    @Test
    void TestGetAmount(){
        Card card1 = mock(Card.class);
        Card card2 = mock(Card.class);
        Card card3 = mock(Card.class);
        Deck deck = new Deck();
        assertTrue(deck.getAmount() == 0);

        deck.addTop(card1);
        assertTrue(deck.getAmount() == 1);
        deck.addTop(card2);
        deck.addTop(card3);

        assertTrue(deck.getAmount() == 3);
    }
    @Test 
    void TestPeekSearchCard(){
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");
        Card card3 = new StdCard(3, 0, "test");

        List<Card> listCards = new ArrayList<>();
        listCards.add(card1);
        listCards.add(card2);
        listCards.add(card3);

        Deck deck = new Deck();
        deck.setListCard(listCards);

        Card searchCard = deck.peekSearchCard(2);

        assertTrue(searchCard.getId() == 2);
        assertEquals(card2, searchCard);
    }
    @Test 
    void TestPeekSearchCardNegative(){
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");
        Card card3 = new StdCard(3, 0, "test");

        List<Card> listCards = new ArrayList<>();
        listCards.add(card1);
        listCards.add(card2);
        listCards.add(card3);

        Deck deck = new Deck();
        deck.setListCard(listCards);

        Card searchCard = deck.peekSearchCard(-2);

        assertNull(searchCard);
    }
    @Test
    void TestPeekSearchCardNotFound(){
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");
        Card card3 = new StdCard(3, 0, "test");

        List<Card> listCards = new ArrayList<>();
        listCards.add(card1);
        listCards.add(card2);
        listCards.add(card3);

        Deck deck = new Deck();
        deck.setListCard(listCards);

        Card searchCard = deck.peekSearchCard(5);

        assertNull(searchCard);
    }
    @Test
    void TestPeekSearchCardEmpySet(){
        Deck deck = new Deck();

        Card searchCard = deck.peekSearchCard(0);

        assertNull(searchCard);
        assertTrue(deck.isEmpty());
    }
    @Test
    void TesttakeSearchCard(){
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");
        Card card3 = new StdCard(3, 0, "test");

        List<Card> listCards = new ArrayList<>();
        listCards.add(card1);
        listCards.add(card2);
        listCards.add(card3);

        Deck deck = new Deck();
        deck.setListCard(listCards);

        int amount = deck.getAmount();
        Card searchCard = deck.takeSearchCard(2);

        assertEquals(card2, searchCard);
        assertTrue(deck.getAmount() == amount-1);
    }
    @Test 
    void TesttakeSearchCardRemoveLastCard(){
        Card card1 = new StdCard(1, 0, "Test");
        Deck deck = new Deck();

        deck.addTop(card1);

        Card searchCard = deck.takeSearchCard(1);

        assertEquals(card1, searchCard);
        assertTrue(deck.isEmpty());
    }
    @Test 
    void TesttakeSearchCardNotFound(){
        Card card1 = new StdCard(1, 0, "Test");
        Deck deck = new Deck();

        deck.addTop(card1);

        Card searchCard = deck.takeSearchCard(3);

        assertNull(searchCard);
        assertTrue(!deck.isEmpty());
    }
    @Test
    void TestSetnGetList(){
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");
        Card card3 = new StdCard(3, 0, "test");

        List<Card> listCards = new ArrayList<>();
        listCards.add(card1);
        listCards.add(card2);
        listCards.add(card3);

        Deck deck = new Deck();

        assertTrue(deck.isEmpty());

        deck.setListCard(listCards);
        assertFalse(deck.isEmpty());
        assertEquals(listCards, deck.getAllCards());
        deck.takeSearchCard(1);
        assertNotEquals(listCards, deck.getAllCards());
    }
}
