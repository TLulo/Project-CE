import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import Card.Card;
import Card.StdCard;
import SetCard.Board;

public class BoardTest {
    @Test 
    void TestConstructor(){
        Board<Card> board = new Board<Card>();

        assertTrue(board.isEmpty());
    }
    @Test 
    void TestAdd(){
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");
        Card card3 = new StdCard(3, 0, "test");

        Board<Card> board = new Board<Card>();

        board.add(card3);
        board.add(card2);
        board.add(card1);

        assertEquals(3, board.getAmount());
    }
    @Test 
    void TestAddindex(){
        Card card1 = new StdCard(1, 0, "test");
        Card card2 = new StdCard(2, 0, "test");
        Card card3 = new StdCard(3, 0, "test");

        Board<Card> board = new Board<Card>();

        board.add(card3);
        board.add(card2);
        board.add(card1,1);
        assertEquals(card1, board.getAllCards().get(1));
    }
}
