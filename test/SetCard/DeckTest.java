import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Card.Card;
import Card.StdCard;
import SetCard.Deck;

public class DeckTest {
    @Test 
    void TestConstructor(){
        Deck<Card> deck = new Deck<Card>();

        assertTrue(deck.isEmpty());
    }
    @Test
    void takeCard(){
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");
        Card card3 = new StdCard(3, 0, "test");

        List<Card> listCards = new ArrayList<>();
        listCards.add(card1);
        listCards.add(card2);
        listCards.add(card3);

        Deck<Card> deck = new Deck<Card>();
        deck.setListCard(listCards);

        assertEquals(card1, deck.takeCard());
        assertEquals(card2, deck.takeCard());
        assertEquals(card3, deck.takeCard());
        assertEquals(null, deck.takeCard());
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
    @Test 
    void TestDeal(){
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");
        Card card3 = new StdCard(3, 0, "test");

        List<Card> listCards = new ArrayList<>();
        listCards.add(card1);
        listCards.add(card2);

        Deck<Card> deck = new Deck<Card>();
        deck.setListCard(listCards);
        deck.addBottom(card3);

        assertEquals(listCards, deck.deal(2));
        assertEquals(1, deck.getAmount());
    }
    @Test 
    void TestDealNegative(){
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");

        List<Card> listCards = new ArrayList<>();
        listCards.add(card1);
        listCards.add(card2);

        Deck<Card> deck = new Deck<Card>();
        deck.setListCard(listCards);

        assertThrowsExactly(IllegalArgumentException.class, ()->deck.deal(-2));
        assertEquals(2, deck.getAmount());
    }
    @Test 
    void TestDealBigHand(){
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");
        Card card3 = new StdCard(3, 0, "test");

        List<Card> listCards = new ArrayList<>();
        listCards.add(card1);
        listCards.add(card2);
        listCards.add(card3);

        Deck<Card> deck = new Deck<Card>();
        deck.setListCard(listCards);

        List<Card> deal = deck.deal(999);
        assertEquals(listCards, deal);
        assertTrue(deck.isEmpty());
        assertEquals(3, deal.size());
        assertNotEquals(999, deal.size());
    }
    @Test 
    void TestAddTop(){
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");
        Card card3 = new StdCard(3, 0, "test");

        Deck<Card> deck = new Deck<Card>();

        deck.addTop(card3);
        deck.addTop(card2);
        deck.addTop(card1);

        assertEquals(3, deck.getAmount());
        assertEquals(card1, deck.takeCard());
        assertEquals(card2, deck.takeCard());
        assertEquals(card3, deck.takeCard());
        assertTrue(deck.isEmpty());
    }
    @Test 
    void TestAddBottom(){
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");
        Card card3 = new StdCard(3, 0, "test");

        Deck<Card> deck = new Deck<Card>();

        deck.addBottom(card1);
        deck.addBottom(card2);
        deck.addBottom(card3);
        

        assertEquals(3, deck.getAmount());
        assertEquals(card1, deck.takeCard());
        assertEquals(card2, deck.takeCard());
        assertEquals(card3, deck.takeCard());
        assertTrue(deck.isEmpty());
    }

    @Test
    void TestAddRandom(){
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");
        Card card3 = new StdCard(3, 0, "test");

        Deck<Card> deck = new Deck<Card>();

        deck.addRandom(card1);
        assertEquals(1, deck.getAmount());
        assertEquals(card1, deck.peekSearchCard(1));

        deck.addRandom(card2);
        deck.addRandom(card3);

        assertEquals(3, deck.getAmount());
        assertEquals(card1, deck.peekSearchCard(1));
        assertEquals(card2, deck.peekSearchCard(2));
        assertEquals(card3, deck.peekSearchCard(3));
    }
    @Test 
    void takeRandomCardTest(){
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");
        Card card3 = new StdCard(3, 0, "test");

        Deck<Card> deck = new Deck<Card>();
        assertEquals(null, deck.takeRandomCard());
        deck.addTop(card3);
        assertEquals(1, deck.getAmount());

        assertEquals(card3, deck.takeRandomCard());
        deck.addTop(card2);
        deck.addTop(card1);

        assertEquals(2, deck.getAmount());

        Card random = deck.takeRandomCard();
        assertTrue(card1 == random || card2 == random);
        assertEquals(1, deck.getAmount());
    }
}