package test.Engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import Card.StdCard;
import Engine.Engine;
import Player.StdPlayer;
import Table.StdTable;

public class EngineTest {
    @Test
    void ConstructorTest(){
        StdTable table = new StdTable(null);

        Engine<StdPlayer<StdCard>,StdCard,StdTable> engine = new Engine<StdPlayer<StdCard>,StdCard,StdTable>(table);
        
        assertTrue(engine.getTable().playersIsEmpty());
        assertEquals(table, engine.getTable());
    }
    @Test
    void getCurrentPlayerTest(){
        StdTable table = new StdTable(null);
        StdPlayer<StdCard> player1 = new StdPlayer<>(0);
        StdPlayer<StdCard> player2 = new StdPlayer<>(0);
        table.addPlayer(player1);
        table.addPlayer(player2);

        Engine<StdPlayer<StdCard>,StdCard,StdTable> engine = new Engine<StdPlayer<StdCard>,StdCard,StdTable>(table);
        assertEquals(player1, engine.getCurrentPlayer());
        assertEquals(player2, engine.nextTurn());
        assertEquals(player2, engine.getCurrentPlayer());
    }
    @Test
    void NullTurnTest(){
        StdTable table = new StdTable(null);

        Engine<StdPlayer<StdCard>,StdCard,StdTable> engine = new Engine<StdPlayer<StdCard>,StdCard,StdTable>(table);
        assertNull(engine.getCurrentPlayer());
        assertNull(engine.nextTurn());
    }
    @Test
    void setGame(){
        StdTable table = mock(StdTable.class);

        Engine<StdPlayer<StdCard>,StdCard,StdTable> engine = new Engine<StdPlayer<StdCard>,StdCard,StdTable>(table);
        engine.setGame(0);
        verify(table).setGame();
    }
}
