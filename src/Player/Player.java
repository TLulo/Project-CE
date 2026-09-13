package Player;

public abstract class Player {
    protected static int nextId = 0;
    protected int playerId;
    protected String name;

    public Player(){
        playerId = nextId ++;
    }

    public int getId(){
        return playerId;
    }

    public boolean matchesId(int playerId){
        return this.playerId == playerId;
    }

    public void setName(String newName){
        name = newName;
    }

    public String getName(){
        return name;
    }

}
