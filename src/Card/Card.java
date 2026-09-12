package Card;

public abstract class Card {
    protected int id;

    public Card(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }

    public boolean matchesId(int id){
        return this.id == id;
    }
}
