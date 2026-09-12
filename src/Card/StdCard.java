package Card;

public class StdCard extends Card{
    private int value;
    private String suit;

    public StdCard (int id, int value, String suit){
        super(id);
        this.value = value;
        this.suit = suit;
    }

    public int getValue(){
        return value;
    }

    public String getSuit(){
        return suit;
    }

}