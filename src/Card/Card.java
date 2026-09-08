package Card;

public abstract class Card {
    private int id;
    
    public int getId(){
        return id;
    }

    public boolean matchesId(int ID){
        return getId() == ID;
    }
}
