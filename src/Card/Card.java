package Card;

public abstract class Card {
    private int id;
    
    public int getId(){
        return id;
    }

    public boolean isThisID(int ID){
        return id == ID;
    }
}
