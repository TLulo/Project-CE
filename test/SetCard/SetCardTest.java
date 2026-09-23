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
        Deck<Card> deck = new Deck<Card>();

        assertTrue(deck.isEmpty());
    }
    @Test
    void TestGetAmount(){
        Card card1 = mock(Card.class);
        Card card2 = mock(Card.class);
        Card card3 = mock(Card.class);
        Deck<Card> deck = new Deck<Card>();
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

        Deck<Card> deck = new Deck<Card>();
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

        Deck<Card> deck = new Deck<Card>();
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

        Deck<Card> deck = new Deck<Card>();
        deck.setListCard(listCards);

        Card searchCard = deck.peekSearchCard(5);

        assertNull(searchCard);
    }
    @Test
    void TestPeekSearchCardEmpySet(){
        Deck<Card> deck = new Deck<Card>();

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

        Deck<Card> deck = new Deck<Card>();
        deck.setListCard(listCards);

        int amount = deck.getAmount();
        Card searchCard = deck.takeSearchCard(2);

        assertEquals(card2, searchCard);
        assertTrue(deck.getAmount() == amount-1);
    }
    @Test 
    void TesttakeSearchCardRemoveLastCard(){
        Card card1 = new StdCard(1, 0, "Test");
        Deck<Card> deck = new Deck<Card>();

        deck.addTop(card1);

        Card searchCard = deck.takeSearchCard(1);

        assertEquals(card1, searchCard);
        assertTrue(deck.isEmpty());
    }
    @Test 
    void TesttakeSearchCardNotFound(){
        Card card1 = new StdCard(1, 0, "Test");
        Deck<Card> deck = new Deck<Card>();

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

        Deck<Card> deck = new Deck<Card>();

        assertTrue(deck.isEmpty());

        deck.setListCard(listCards);
        assertFalse(deck.isEmpty());
        assertEquals(listCards, deck.getAllCards());
        deck.takeSearchCard(1);
        assertNotEquals(listCards, deck.getAllCards());
    }
    @Test
    void TestShuffle(){
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");

        List<Card> listCards = new ArrayList<>();
        listCards.add(card1);
        listCards.add(card2);

        Deck<Card> deck = new Deck<Card>();
        deck.setListCard(listCards);

        deck.shuffle();

        assertEquals(2, deck.getAmount());
        assertNull(deck.peekSearchCard(0));
        assertEquals(card1, deck.peekSearchCard(1));
        assertEquals(card2, deck.peekSearchCard(2));
        assertNull(deck.peekSearchCard(3));
    }
}
