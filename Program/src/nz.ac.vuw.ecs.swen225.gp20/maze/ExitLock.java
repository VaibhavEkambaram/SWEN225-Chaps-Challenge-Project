package nz.ac.vuw.ecs.swen225.gp20.maze;

public class ExitLock extends Tile {
    private int chipsNeeded;

    public ExitLock(){
        super(Tiles.ExitLock);
        this.isPassable = false;
        this.image = "exit_lock.png"; //we dont have one of these yet I don't think
    }

    @Override
    public boolean action(Player p) {
        if(chipsNeeded == p.getChips()){
            image = "empty.png";
            isPassable = true;
        }
        return isPassable;
    }

    @Override
    public String toString() {return "exit_lock";}

    //Getters and Setters
    public void setChipsNeeded(int numberOfChips){chipsNeeded = numberOfChips;}
    public int getChipsNeeded(){return chipsNeeded;}
}
