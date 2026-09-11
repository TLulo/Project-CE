package Engine;

import Table.Table;

public class Engine {
    protected Table table;
    protected int currentTurn;

    public Engine(Table table){
        this.table = table;
        this.currentTurn = 0;
    }
}